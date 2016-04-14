package main.java.kontabill.mvc.view.view_commands.concrete;

import main.java.kontabill.layout.elements.forms.FormLayoutBaseAbstract;
import main.java.kontabill.layout.elements.forms.FormLayoutControlPanel;
import main.java.kontabill.mvc.controller.CatalogController;
import main.java.kontabill.mvc.model.forms.DelegatForm;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.view.BaseAbstractView;
import main.java.kontabill.mvc.view.ComponentRegistry;
import main.java.kontabill.mvc.view.catalog.CatalogDelegatesView;
import main.java.kontabill.mvc.view.view_commands.Command;


/**
 *
 */
public class ShowAddDelegatFormCommand implements Command {

    private BaseAbstractView view;

    @Override
    public void execute(BaseAbstractView view) {

        this.view = view;
        CatalogDelegatesView catalogDelegatesView = (CatalogDelegatesView) view;

        BaseAbstractForm form = getDelegatForm();
        FormLayoutBaseAbstract formLayout = new FormLayoutControlPanel(form, catalogDelegatesView.getPanelForm());

        catalogDelegatesView.getSubmitButtonForm().setText("Adauga delegat"); // add label to submit button

        form.registerSubmitButton(catalogDelegatesView.getSubmitButtonForm(),  () -> {
            if (formLayout.validate() == true) {
                view.getRequest().removeDataItem("checkedEntitiesDelegatTableModel");
                ((CatalogController) view.getControllerForView()).addDelegatAction(form);
            }
        });
    }

    private DelegatForm getDelegatForm()
    {
        String key = view.getControllerForView().getControllerName() + "DelegatForm";

        if(ComponentRegistry.getComponent(key) == null) {
            DelegatForm delegatForm = new DelegatForm();
            ComponentRegistry.addComponent(key, delegatForm);
        }

        return (DelegatForm) ComponentRegistry.getComponent(key);
    }
}
