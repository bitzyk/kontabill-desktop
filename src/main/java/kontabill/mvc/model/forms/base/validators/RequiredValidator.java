package main.java.kontabill.mvc.model.forms.base.validators;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.mvc.model.forms.base.Validator;
import main.java.kontabill.mvc.model.forms.base.ValidatorBaseAbstract;
import main.java.kontabill.mvc.model.forms.base.ValidatorConfig;
import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class RequiredValidator extends ValidatorBaseAbstract implements Validator {

    private static final String ERROR_TEMPLATE = "Completarea campului `%s` este obligatorie.";

    public RequiredValidator(ValidatorConfig validatorConfig, FormElement formElement)
    {
        super(validatorConfig, formElement);
    }

    /**
     * @param value
     * @return
     */
    @Override
    public boolean validate(String value)
    {
        clearErrors();

        boolean valid = true;

        if (StringUtils.isBlank(value) == true) {
            valid = false;
            addError(
                    String.format(ERROR_TEMPLATE, getFormElement().getLabel().toLowerCase())
            );
        }

        return valid;
    }

    @Override
    protected void parseValidatorConfiguration() {

    }
}
