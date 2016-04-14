package main.java.kontabill.mvc.view.catalog;

import main.java.kontabill.layout.ViewUtils;
import main.java.kontabill.layout.elements.factories.ButtonFactory;
import main.java.kontabill.layout.elements.forms.FormLayoutBaseAbstract;
import main.java.kontabill.layout.elements.forms.FormLayoutControlPanel;
import main.java.kontabill.layout.elements.forms.FormLayoutDialog;
import main.java.kontabill.layout.elements.tables.TableDefault;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.ViewLayout;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.model.RowTypePanels;
import main.java.kontabill.lib.core.request.Request;
import main.java.kontabill.mvc.controller.BaseAbstractController;
import main.java.kontabill.mvc.controller.CatalogController;
import main.java.kontabill.mvc.model.core.SubscribeableHashMap;
import main.java.kontabill.mvc.model.core.SubscribeableHashMapListener;
import main.java.kontabill.mvc.model.entities.Client;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.table_models.ClientTableModel;
import main.java.kontabill.mvc.model.forms.ClientForm;
import main.java.kontabill.mvc.model.forms.DelegatForm;
import main.java.kontabill.mvc.model.forms.SearchFormTable;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.view.BaseAbstractView;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 */
public class CatalogClientsView extends BaseAbstractView  {

    private SubscribeableHashMap<Integer, Client> clientsHashMap;

    private TableDefault tableClients = new TableDefault();

    private final ViewLayout viewLayout = new ViewLayout(getLayout());


    private final String VIEW_BUTTON_ADD_CLIENT = "addClients";

    private final String VIEW_BUTTON_SEARCH_CLIENTS = "searchClients";

    private final String VIEW_BUTTON_EXPORT_CLIENTS = "exportClients";

    private final String VIEW_BUTTON_IMPORT_CLIENTS = "importClients";


    private String[][] viewButtonPanelControl = {
            {
                    "Adaugare client",
                    VIEW_BUTTON_ADD_CLIENT,
            },
            {
                    "Cautare",
                    VIEW_BUTTON_SEARCH_CLIENTS,
            },
            {
                    "Export",
                    VIEW_BUTTON_EXPORT_CLIENTS,
            },
            {
                    "Import",
                    VIEW_BUTTON_IMPORT_CLIENTS,
            }
    };


    public CatalogClientsView(BaseAbstractController controller, SubscribeableHashMap<Integer, Client> clientsHashMap) {
        super(controller);
        this.clientsHashMap = clientsHashMap;
    }


    @Override
    public void render()
    {
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
        if (this.isViewButtonActive(VIEW_BUTTON_SEARCH_CLIENTS)) {
            submitButton = ButtonFactory.createButtonGreenSubmitControlPanel("Cauta client");
            BaseAbstractForm form = new SearchFormTable("Nume/Cnp/Cui", tableClients, submitButton);

            FormLayoutBaseAbstract formLayout = new FormLayoutControlPanel(form, rowPanel3);
        } else if (this.isViewButtonActive(VIEW_BUTTON_ADD_CLIENT)) {
            BaseAbstractForm form = new ClientForm();
            FormLayoutBaseAbstract formLayout = new FormLayoutControlPanel(form, rowPanel3);

            submitButton = ButtonFactory.createButtonGreenSubmitControlPanel("Adauga client");

            form.registerSubmitButton(submitButton, () -> {
                if (formLayout.validate() == true) {
                    getRequest().removeDataItem("checkedEntitiesClientTableModel");
                    ((CatalogController) getControllerForView()).addClientAction(form);
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
        JButton deleteClientsButton = ButtonFactory.createButtonDeleteDefault("Sterge client/clienti");
        JButton editClientButton = ButtonFactory.createButtonEditDefault("Editeaza client");

        // add table control buttons to row panel
        panelTableRow1.add(deleteClientsButton);
        ViewUtils.addComponentPadding(deleteClientsButton, panelTableRow1);
        panelTableRow1.add(editClientButton);

        // add row2 in panel table section
        JPanel panelTableRow2 = viewLayout.getPanelTable().addRowPanel(RowTypePanels.TABLE);
        // add table component in panelTable row reference
        viewLayout.getPanelTable().addTableToPanel(panelTableRow2, tableClients);

        // add hash map listener for delegates references (it is runned in a thread)
        SubscribeableHashMapListener<Integer, Client> subscribeableHashMapListener = new SubscribeableHashMapListener<Integer, Client>() {
            @Override
            public void run(HashMap<Integer, Client> clientsHashMap) {

                // set table with model
                ClientTableModel clientTableModel = new ClientTableModel(clientsHashMap);
                clientTableModel.initTableModelActivityListener(request);

                tableClients.setModel(clientTableModel);

                // set/reset table checked values to the previous state
                if (! getRequest().hasDataItem("checkedEntitiesClientTableModel")) {
                    Boolean[] checkedEntitiesClientTableModel = clientTableModel.getCheckedEntitiesArray();
                    getRequest().addDataItem("checkedEntitiesClientTableModel", checkedEntitiesClientTableModel);
                } else {
                    Boolean[] checkedEntitiesClientTableModel = (Boolean[])getRequest().getDataItem("checkedEntitiesClientTableModel");
                    clientTableModel.setCheckedEntitiesArray(checkedEntitiesClientTableModel);
                }


                // add listener to delete delegates button
                deleteClientsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // if no delegates was selected warn
                        if (!clientTableModel.hasSelectedRows()) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Nu ati selectat clienti pentru a fi stersi din baza de date!",
                                    "Comanda invalida",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        } else {
                            int noSelectedRows = clientTableModel.countSelectedRows();

                            int option = JOptionPane.showConfirmDialog(
                                    null,
                                    "Ati selectat un numar de " + noSelectedRows + " clienti." +
                                            "\nConfirmati stergerea ?",
                                    "Confirmati stergerea",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (option == JOptionPane.YES_OPTION) {
                                // call delete delegates for selected delegates
                                ArrayList<Client> clients = clientTableModel.getSelectedEntities();

                                // reset checked values to not be auto-populated on future request (could be less rows that curently are)
                                getRequest().addDataItem("checkedEntitiesClientTableModel", null);

                                // call method controller for deleting delegates
                                ((CatalogController) getControllerForView()).deleteClientsAction(clients);
                            }
                        }
                    }
                });


                editClientButton.addActionListener(e -> {
                    if (!clientTableModel.hasSelectedRows()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Nu ati selectat clienti pentru a fi editati!",
                                "Comanda invalida",
                                JOptionPane.WARNING_MESSAGE
                        );
                    } else {

                        int noSelectedRows = clientTableModel.countSelectedRows();

                        if (noSelectedRows > 5) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Puteti edita un numar de maxim 5 clienti simultan",
                                    "Comanda invalida",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        } else {
                            ArrayList<Client> clients = clientTableModel.getSelectedEntities();

                            for (int i = 0; i < clients.size(); i++) {

                                Client client = clients.get(i);

                                BaseAbstractForm form = new ClientForm();
                                form.hydrateForm(client);

                                FormLayoutDialog formLayout = new FormLayoutDialog(
                                        form,
                                        new JPanel(),
                                        i,
                                        "Editare",
                                        "Editeaza client `" + client.getName() + "`"
                                );
                                formLayout.showForm();

                                formLayout.registerSubmitButtonRunner(() -> {
                                    if (formLayout.validate() == true) {
                                        getRequest().removeDataItem("checkedEntitiesClientTableModel");
                                        ((CatalogController) getControllerForView()).editClientAction(client, form);
                                    }
                                });
                            }

                        }
                    }
                });


                // add listener to table
                tableClients.getModel().addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {

                        ClientTableModel clientTableModel1 = (ClientTableModel) e.getSource();
                        int row = e.getFirstRow();
                        int column = e.getColumn();

                        Object value = clientTableModel1.getValueAt(row, column);

                        // if the column is boolean then checkbox has been triggered
                        // update info number of selecteed rows
                        if (value instanceof Boolean) {
                        }

                    }
                });
            }
        };

        // if thread child is already finished run hashMapListener directly
        if(clientsHashMap.isThreadFinished()) {
            subscribeableHashMapListener.run(clientsHashMap.getObservedMap());
        }
        // if thread child is not finished (normally the case when is run first time), then subscribe hashMapListener
        // to the map resource
        else {
            clientsHashMap.addHashMapListener(subscribeableHashMapListener);
        }
    }

    private Boolean isViewButtonActive(String viewButtonIdentifier)
    {
        if(! getRequest().hasDataItem("showForm")
                && viewButtonIdentifier.equals(VIEW_BUTTON_SEARCH_CLIENTS)) {
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
                    e.getActionCommand() == VIEW_BUTTON_ADD_CLIENT ||
                    e.getActionCommand() == VIEW_BUTTON_SEARCH_CLIENTS
            ) {
                Request request = new Request();
                request.addDataItem("showForm", e.getActionCommand());
                getMvc().runController("catalogClientsAction", request);
            }

        }

    }



}
