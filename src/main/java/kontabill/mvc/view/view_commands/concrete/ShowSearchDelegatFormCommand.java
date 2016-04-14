package main.java.kontabill.mvc.view.view_commands.concrete;

import main.java.kontabill.layout.elements.factories.ButtonFactory;
import main.java.kontabill.layout.elements.forms.FormLayoutBaseAbstract;
import main.java.kontabill.layout.elements.forms.FormLayoutControlPanel;
import main.java.kontabill.layout.elements.tables.TableDefault;
import main.java.kontabill.mvc.controller.CatalogController;
import main.java.kontabill.mvc.model.forms.DelegatForm;
import main.java.kontabill.mvc.model.forms.SearchFormTable;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.view.BaseAbstractView;
import main.java.kontabill.mvc.view.catalog.CatalogDelegatesView;
import main.java.kontabill.mvc.view.view_commands.Command;

import javax.swing.*;

/**
 * Created by cbitoi on 28/02/16.
 */
public class ShowSearchDelegatFormCommand implements Command {

    private BaseAbstractView view;

    @Override
    public void execute(BaseAbstractView view) {

        this.view = view;
        CatalogDelegatesView catalogDelegatesView = (CatalogDelegatesView) view;

        this.view = view;

        catalogDelegatesView.getSubmitButtonForm().setText("Cauta delegat");
        BaseAbstractForm form = new SearchFormTable(
                "Nume delegat",
                catalogDelegatesView.getTableDelegates(),
                catalogDelegatesView.getSubmitButtonForm()
        );

        FormLayoutBaseAbstract formLayout = new FormLayoutControlPanel(form, catalogDelegatesView.getPanelForm());
    }
}
