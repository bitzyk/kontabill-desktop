package main.java.kontabill.mvc.view.catalog;

import main.java.kontabill.Kontabill;
import main.java.kontabill.layout.ViewUtils;
import main.java.kontabill.layout.elements.factories.ButtonFactory;
import main.java.kontabill.layout.elements.forms.FormLayout;
import main.java.kontabill.layout.elements.forms.FormLayoutBaseAbstract;
import main.java.kontabill.layout.elements.forms.FormLayoutControlPanel;
import main.java.kontabill.layout.elements.forms.FormLayoutDialog;
import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.layout.elements.tables.TableDefault;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.ViewLayout;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.model.RowTypePanels;
import main.java.kontabill.mvc.Request;
import main.java.kontabill.mvc.controller.BaseAbstractController;
import main.java.kontabill.mvc.controller.CatalogController;
import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.core.SubscribeableHashMapListener;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.table_models.DelegatTableModel;
import main.java.kontabill.mvc.model.forms.DelegatForm;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.view.BaseAbstractView;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 */
public class CatalogDelegatesView extends BaseAbstractView  {

    private SubscribeableHashMap delegatesHashMap;

    private final ViewLayout viewLayout = new ViewLayout(getLayout());

    private final String VIEW_BUTTON_ADD_DELEGAT = "addDelegat";

    private final String VIEW_BUTTON_SEARCH_DELEGATES = "searchDelegates";

    private final String VIEW_BUTTON_EXPORT_DELEGATES = "exportDelegates";

    private final String VIEW_BUTTON_IMPORT_DELEGATES = "importDelegates";

    private String[][] viewButtonPanelControl = {
            {
                    "Adaugare delegat",
                    VIEW_BUTTON_ADD_DELEGAT,
            },
            {
                    "Cautare",
                    VIEW_BUTTON_SEARCH_DELEGATES,
            },
            {
                    "Export",
                    VIEW_BUTTON_EXPORT_DELEGATES,
            },
            {
                    "Import",
                    VIEW_BUTTON_IMPORT_DELEGATES,
            }
    };


    public CatalogDelegatesView(BaseAbstractController controller, SubscribeableHashMap delegatesHashMap) {
        super(controller);
        this.delegatesHashMap = delegatesHashMap;
    }


    @Override
    public void render() {

        renderPanelControl();
        renderPanelTable();

    }


    private void renderPanelControl()
    {
        // add rowPanel 1 and buttons
        JPanel rowPanel1 = viewLayout.getPanelControl().addRowPanel(RowTypePanels.DEFAULT);
        viewLayout.getPanelControl().addSubmenusButtonToPanel(rowPanel1, this);

        // add row panel 2 and buttons
        JPanel rowPanel2 = viewLayout.getPanelControl().addRowPanel(RowTypePanels.DEFAULT);

        // add view buttons to rowPanel2
        for (String[] buttonConfig : viewButtonPanelControl) {
            Boolean isActive = this.isViewButtonActive(buttonConfig[1]);
            JButton button = ButtonFactory.createButtonWhiteControlPanel(buttonConfig[0], isActive);
            button.setActionCommand(buttonConfig[1]);
            ViewUtils.addComponentPadding(
                    button,
                    rowPanel2
            );
            button.addActionListener(new ViewButtonActionListener());
        }

        // add row panel form
        JPanel rowPanel3 = viewLayout.getPanelControl().addRowPanel(RowTypePanels.FORM);


        JButton submitButton = null;

        // add search formular by default when showForm does not exist in request
        if (this.isViewButtonActive(VIEW_BUTTON_SEARCH_DELEGATES)) {
            FormLayout searchDelegatForm = new FormLayout(rowPanel3);

            searchDelegatForm.addInputs(new String[][]{
                    {InputType.TEXT_FIELD.toString(), "Nume delegat"},
            });
            submitButton = ButtonFactory.createButtonGreenSubmitControlPanel("Cauta delegat");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // action listener for search delegat
                }
            });
        } else if (this.isViewButtonActive(VIEW_BUTTON_ADD_DELEGAT)) {
            BaseAbstractForm form = new DelegatForm();
            FormLayoutBaseAbstract formLayout = new FormLayoutControlPanel(form, rowPanel3);

            submitButton = ButtonFactory.createButtonGreenSubmitControlPanel("Adauga delegat");

            form.registerSubmitButton(submitButton,  () -> {
                if (formLayout.validate() == true) {
                    getRequest().removeDataItem("checkedEntitiesDelegatTableModel");
                    ((CatalogController) getControllerForView()).addDelegatAction(form);
                }
            });
        }

        // add row panel 4 and submit button
        JPanel rowPanel4 = viewLayout.getPanelControl().addRowPanel(RowTypePanels.DEFAULT);

        rowPanel4.add(submitButton);
    }

    private void renderPanelTable()
    {
        // add row1 in panel table section
        JPanel panelTableRow1 = viewLayout.getPanelTable().addRowPanel(RowTypePanels.DEFAULT);
        JButton deleteDelegatesButton = ButtonFactory.createButtonDeleteDefault("Sterge delegat/delegati");
        JButton editDelegatButton = ButtonFactory.createButtonEditDefault("Editeaza delegat");

        // add table control buttons to row panel
        panelTableRow1.add(deleteDelegatesButton);
        ViewUtils.addComponentPadding(deleteDelegatesButton, panelTableRow1);
        panelTableRow1.add(editDelegatButton);

        // add row2 in panel table section
        JPanel panelTableRow2 = viewLayout.getPanelTable().addRowPanel(RowTypePanels.TABLE);
        // add table component in panelTable row reference
        TableDefault table = viewLayout.getPanelTable().addTable(panelTableRow2);

        // add hash map listener for delegates references (it is runned in a thread)
        SubscribeableHashMapListener subscribeableHashMapListener = new SubscribeableHashMapListener() {
            @Override
            public void run(HashMap<Object, Object> delegatesHashMap) {

                // set table with model
                DelegatTableModel delegatTableModel = new DelegatTableModel(delegatesHashMap);
                table.setModel(delegatTableModel);

                // set/reset table checked values to the previous state
                if (! getRequest().hasDataItem("checkedEntitiesDelegatTableModel")) {
                    Boolean[] checkedEntitiesDelegatTableModel = delegatTableModel.getCheckedEntitiesArray();
                    getRequest().addDataItem("checkedEntitiesDelegatTableModel", checkedEntitiesDelegatTableModel);
                } else {
                    Boolean[] checkedEntitiesDelegatTableModel = (Boolean[])getRequest().getDataItem("checkedEntitiesDelegatTableModel");
                    delegatTableModel.setCheckedEntitiesArray(checkedEntitiesDelegatTableModel);
                }


                // add listener to delete delegates button
                deleteDelegatesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // if no delegates was selected warn
                        if (! delegatTableModel.hasSelectedRows()) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Nu ati selectat delegati pentru a fi stersi din baza de date!",
                                    "Comanda invalida",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        }
                        else {
                            int noSelectedRows = delegatTableModel.countSelectedRows();

                            int option = JOptionPane.showConfirmDialog(
                                    null,
                                    "Ati selectat un numar de " + noSelectedRows + " delegati." +
                                            "\nConfirmati stergerea ?",
                                    "Confirmati stergerea",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (option == JOptionPane.YES_OPTION) {
                                // call delete delegates for selected delegates
                                ArrayList<Delegat> delegats = delegatTableModel.getSelectedEntities();

                                // reset checked values to not be auto-populated on future request (could be less rows that curently are)
                                getRequest().addDataItem("checkedEntitiesDelegatTableModel", null);

                                // call method controller for deleting delegates
                                ((CatalogController)getControllerForView()).deleteDelegatesAction(delegats);
                            }
                        }
                    }
                });


                editDelegatButton.addActionListener(e -> {
                    if (! delegatTableModel.hasSelectedRows()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Nu ati selectat delegati pentru a fi editati!",
                                "Comanda invalida",
                                JOptionPane.WARNING_MESSAGE
                        );
                    } else {

                        int noSelectedRows = delegatTableModel.countSelectedRows();

                        if(noSelectedRows > 5) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Puteti edita un numar de maxim 5 delegati simultan",
                                    "Comanda invalida",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        }
                        else {
                            ArrayList<Delegat> delegats = delegatTableModel.getSelectedEntities();

                            for (int i = 0; i < delegats.size(); i++) {

                                Delegat delegat = delegats.get(i);

                                BaseAbstractForm form = new DelegatForm();
                                FormLayoutDialog formLayout = new FormLayoutDialog(form, new JPanel(), i);
                                formLayout.showForm();

                                formLayout.registerSubmitButtonRunner(() -> {
                                    if (formLayout.validate() == true) {
                                        getRequest().removeDataItem("checkedEntitiesDelegatTableModel");
                                        ((CatalogController) getControllerForView()).addDelegatAction(form);
                                    }
                                });
                            }

                        }
                    }
                });


                // add listener to table
                table.getModel().addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {

                        DelegatTableModel delegatTableModel = (DelegatTableModel) e.getSource();
                        int row = e.getFirstRow();
                        int column = e.getColumn();

                        Object value = delegatTableModel.getValueAt(row, column);

                        // if the column is boolean then checkbox has been triggered
                        // update info number of selecteed rows
                        if (value instanceof Boolean) {
                        }

                    }
                });
            }
        };

        // if thread child is already finished run hashMapListener directly
        if(delegatesHashMap.isThreadFinished()) {
            subscribeableHashMapListener.run(delegatesHashMap.getObservedMap());
        }
        // if thread child is not finished (normally the case when is run first time), then subscribe hashMapListener
        // to the map resource
        else {
            delegatesHashMap.addHashMapListener(subscribeableHashMapListener);
        }
    }

    private Boolean isViewButtonActive(String viewButtonIdentifier)
    {
        if(! getRequest().hasDataItem("showForm")
                && viewButtonIdentifier.equals(VIEW_BUTTON_SEARCH_DELEGATES)) {
            return true;
        }

        if(viewButtonIdentifier.equals(getRequest().getDataItem("showForm"))) {
            return true;
        }

        return false;
    }

    class ViewButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // when form buttons are pressed toggle forms
            if(
                    e.getActionCommand() == VIEW_BUTTON_ADD_DELEGAT ||
                    e.getActionCommand() == VIEW_BUTTON_SEARCH_DELEGATES
            ) {
                getRequest().addDataItem("showForm", e.getActionCommand());
                getMvc().runController("catalogDelegatesAction", request);
            }

        }

    }



}
