package main.java.kontabill.mvc.model.forms.base;

import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.mvc.model.forms.base.validators.*;

/**
 *
 */
abstract public class ValidatorFactory {

    public static Validator createValidator(ValidatorConfig validatorConfig, FormElement formElement)
    {
        Validator validator = null;

        if (validatorConfig.getValidatorType() == ValidatorType.MAX_LENGTH) {
            validator = new MaxLengthValidator(validatorConfig, formElement);
        } else if (validatorConfig.getValidatorType() == ValidatorType.MIN_LENGTH) {
            validator = new MinLengthValidator(validatorConfig, formElement);
        } else if (validatorConfig.getValidatorType() == ValidatorType.REQUIRED) {
            validator = new RequiredValidator(validatorConfig, formElement);
        } else if (validatorConfig.getValidatorType() == ValidatorType.REQUIRED_DROPDOWN) {
            validator = new RequiredDropdownValidator(validatorConfig, formElement);
        } else if (validatorConfig.getValidatorType() == ValidatorType.DECIMAL) {
            validator = new DecimalValidator(validatorConfig, formElement);
        } else if (validatorConfig.getValidatorType() == ValidatorType.ALPHA_NUMERIC) {
            validator = new AlphaNumericValidator(validatorConfig, formElement);
        }

        return validator;
    }
}
