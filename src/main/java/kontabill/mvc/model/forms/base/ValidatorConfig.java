package main.java.kontabill.mvc.model.forms.base;

/**
 *
 */
public class ValidatorConfig {

    private ValidatorType validatorType;

    private String[] validatorConfiguration;

    public ValidatorConfig(ValidatorType validatorType, String[] validatorConfiguration) {
        this.validatorType = validatorType;
        this.validatorConfiguration = validatorConfiguration;
    }

    public ValidatorType getValidatorType() {
        return validatorType;
    }

    public String[] getValidatorConfiguration() {
        return validatorConfiguration;
    }
}
