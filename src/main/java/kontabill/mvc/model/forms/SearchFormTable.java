package main.java.kontabill.mvc.model.forms;

import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.layout.elements.tables.TableDefault;
import main.java.kontabill.lib.core.functional_interfaces.BlockRunner;
import main.java.kontabill.mvc.model.entities.Delegat;
import main.java.kontabill.mvc.model.entities.Entity;
import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.LegalEntityDetailPerson;
import main.java.kontabill.mvc.model.entities.table_models.base.BaseAbstract;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.model.forms.base.ValidatorConfig;
import main.java.kontabill.mvc.model.forms.base.ValidatorType;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class SearchFormTable extends BaseAbstractForm {

    private static final String KEY_SEARCH_ELEMENT_NAME = "searchInputTerm";

    private TableDefault table;

    private final BlockRunner searchActionRunner = () -> {
        if(table instanceof TableDefault) {

            FormElement formElement =  getFormElement(KEY_SEARCH_ELEMENT_NAME);

            // set current filter term from input to rowFilter attached to the table
            table.getTableRowSorter().getRowFilter().setFilterTerm(
                    formElement.getValue()
            );

            if (table.getModel() instanceof BaseAbstract) {
                ((BaseAbstract) table.getModel()).fireTableDataChanged();
            }
        }
    };


    /**
     * Definition :
     *  - input type
     *  - input label
     *  - input key
     *  - group key- group key
     */
    private static String[][] ELEMENTS_DEFINITION =
    {
            {
                    InputType.TEXT_FIELD.toString(),
                    "Identificator",
                    KEY_SEARCH_ELEMENT_NAME,
                    "",
            },
    };

    // VALIDATORS DEFINITION
    private static final Map<String, List<ValidatorConfig>> ELEMENTS_VALIDATORS = new HashMap<>();
    // END VALIDATORS DEFINITION


    public SearchFormTable(String elementSearchLabel, TableDefault table, JButton submitButton)
    {
        super();
        super.registerSubmitButton(submitButton, searchActionRunner);

        ELEMENTS_DEFINITION[0][1] = elementSearchLabel;
        this.table = table;

        initInputSearchValidator();
    }

    private void initInputSearchValidator()
    {
        FormElement formElement =  getFormElement(KEY_SEARCH_ELEMENT_NAME);

        JComponent formComponent = (JComponent) formElement;

        formComponent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchActionRunner.run();
            }
        });
    }

    @Override
    public String[][] getElementsDefinition() {
        return ELEMENTS_DEFINITION;
    }

    @Override
    protected Map<String, List<ValidatorConfig>> getElementsValidatorsConfig() {
        return ELEMENTS_VALIDATORS;
    }

    @Override
    public void hydrateEntity(Entity entity)
    {
    }

    public void hydrateForm(Entity entity)
    {
    }
}
