package main.java.kontabill.mvc.model.forms.base.validators;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.mvc.model.forms.base.Validator;
import main.java.kontabill.mvc.model.forms.base.ValidatorBaseAbstract;
import main.java.kontabill.mvc.model.forms.base.ValidatorConfig;

/**
 *
 */
public class MaxLengthValidator extends ValidatorBaseAbstract implements Validator {

    private int maxLength;

    private static final String ERROR_TEMPLATE = "Valoarea campului `%s` este prea lunga, aceasta trebuie sa contina maxim %d caractere.";

    public MaxLengthValidator(ValidatorConfig validatorConfig, FormElement formElement) {
        super(validatorConfig, formElement);
    }

    @Override
    public boolean validate(String value)
    {
        clearErrors();

        boolean isValid = true;

        if(value.trim().length() > maxLength) {
            isValid = false;
            addError(
                    String.format(
                            ERROR_TEMPLATE, getFormElement().getLabel(),
                            maxLength
                    )
            );
        }

        return isValid;
    }

    @Override
    protected void parseValidatorConfiguration() {
        String[] configuration = getValidatorConfig().getValidatorConfiguration();

        if (configuration.length != 1) {
            throw new RuntimeException("Configuration for MaxLengthValidator is invalid");
        }

        maxLength = Integer.parseInt(configuration[0]);

    }
}
