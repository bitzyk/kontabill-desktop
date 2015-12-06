package main.java.kontabill.layout.elements.factories;

import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.layout.elements.inputs.TextFieldForm;
import main.java.kontabill.mvc.model.forms.base.ElementConfig;

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
        }

        // add input label to the formElement
        input.setLabel(elementConfig.getInputLabel());

        return input;
    }


}
