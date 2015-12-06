package main.java.kontabill.mvc.model.forms.base.validators;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.mvc.model.forms.base.Validator;
import main.java.kontabill.mvc.model.forms.base.ValidatorBaseAbstract;
import main.java.kontabill.mvc.model.forms.base.ValidatorConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.derby.iapi.util.StringUtil;

/**
 *
 */
public class MinLengthValidator extends ValidatorBaseAbstract implements Validator {

    private int minLength;

    private static final String ERROR_TEMPLATE = "Valoarea campului `%s` este prea scurta, aceasta trebuie sa contina minim %d caractere.";

    public MinLengthValidator(ValidatorConfig validatorConfig, FormElement formElement) {
        super(validatorConfig, formElement);
    }

    @Override
    public boolean validate(String value) {
        clearErrors();

        boolean isValid = true;

        if(value.trim().length() < minLength) {
            isValid = false;
            addError(
                    String.format(
                            ERROR_TEMPLATE, getFormElement().getLabel(),
                            minLength
                    )
            );
        }

        return isValid;
    }

    @Override
    protected void parseValidatorConfiguration() {
        String[] configuration = getValidatorConfig().getValidatorConfiguration();

        if (configuration.length != 1) {
            throw new RuntimeException("Configuration for MinLengthValidator is invalid");
        }

        minLength = Integer.parseInt(configuration[0]);

    }
}
