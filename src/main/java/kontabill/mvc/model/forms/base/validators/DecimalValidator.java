package main.java.kontabill.mvc.model.forms.base.validators;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.mvc.model.forms.base.Validator;
import main.java.kontabill.mvc.model.forms.base.ValidatorBaseAbstract;
import main.java.kontabill.mvc.model.forms.base.ValidatorConfig;

/**
 *
 */
public class DecimalValidator extends ValidatorBaseAbstract implements Validator {

    private static final String REG_EXP_DECIMAL = "[0-9]+";

    private static final String ERROR_TEMPLATE = "Valoarea campului `%s` este invalida. Specificati valori decimale [0-9].";

    public DecimalValidator(ValidatorConfig validatorConfig, FormElement formElement) {
        super(validatorConfig, formElement);
    }

    @Override
    public boolean validate(String value) {

        boolean isValid = false;

        if(value.matches(REG_EXP_DECIMAL) == true) {
            isValid = true;
        } else {
            addError(
                    String.format(
                            ERROR_TEMPLATE, getFormElement().getLabel()
                    )
            );
        }

        return isValid;
    }

    @Override
    protected void parseValidatorConfiguration() {}
}
