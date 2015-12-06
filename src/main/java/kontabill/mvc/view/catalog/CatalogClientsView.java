package main.java.kontabill.mvc.view.catalog;

import main.java.kontabill.layout.ViewUtils;
import main.java.kontabill.layout.elements.factories.ButtonFactory;
import main.java.kontabill.layout.elements.forms.FormLayout;
import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.ViewLayout;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.model.RowTypePanels;
import main.java.kontabill.mvc.Request;
import main.java.kontabill.mvc.controller.BaseAbstractController;
import main.java.kontabill.mvc.view.BaseAbstractView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 */
public class CatalogClientsView extends BaseAbstractView  {

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


    public CatalogClientsView(BaseAbstractController controller) {
        super(controller);
    }

    @Override
    public void render() {

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

        // add search formular by default when showForm does not exist in request
        if (this.isViewButtonActive(VIEW_BUTTON_SEARCH_CLIENTS)) {
            FormLayout searchClientForm = new FormLayout(rowPanel3);

            searchClientForm.addInputs(new String[][]{
                    {InputType.TEXT_FIELD.toString(), "Denumire client:"},
                    {InputType.TEXT_FIELD.toString(), "Cui/Cnp client:"},
            });
        } else if (this.isViewButtonActive(VIEW_BUTTON_ADD_CLIENT)) {
            FormLayout addClientForm = new FormLayout(rowPanel3);
            addClientForm.addInputs(new String[][]{
                    {InputType.TEXT_FIELD.toString(), "Nume"},
                    {InputType.TEXT_FIELD.toString(), "Prenume"},
                    {InputType.TEXT_FIELD.toString(), "Cnp"},
                    {InputType.TEXT_FIELD.toString(), "Serie de buletin"},
                    {InputType.TEXT_FIELD.toString(), "Nr buletin"},
                    {InputType.TEXT_FIELD.toString(), "Adresa"},
            });
        }


        // add row panel 4 and submit button
        JPanel rowPanel4 = viewLayout.getPanelControl().addRowPanel(RowTypePanels.DEFAULT);
        rowPanel4.add(ButtonFactory.createButtonGreenSubmitControlPanel("Cauta client"));
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
