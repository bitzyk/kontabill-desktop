package main.java.kontabill.layout.elements.factories;

import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.layout.elements.inputs.ComboBoxForm;
import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.layout.elements.inputs.TextFieldForm;
import main.java.kontabill.mvc.model.forms.base.ElementConfig;
import main.java.kontabill.mvc.model.forms.base.list_model.ComboListModelFactory;

/**
 *
 */
abstract public class FormElementFactory {

    public static FormElement createFormElement(ElementConfig elementConfig)
    {
        FormElement input = null;

        if(elementConfig.getInputType() == InputType.TEXT_FIELD) {
            input = new TextFieldForm();
        } else if (elementConfig.getInputType() == InputType.CHECKBOX) {
            //
        } else if (elementConfig.getInputType() == InputType.DROPDOWN) {
            ComboBoxForm comboBoxForm = new ComboBoxForm();
            comboBoxForm.setModel(
                    ComboListModelFactory.createListModel(
                            elementConfig.getInputKey()
                    )
            );
            input = (FormElement) comboBoxForm;

        }
        // add input label to the formElement
        input.setLabel(elementConfig.getInputLabel());

        return input;
    }


}
