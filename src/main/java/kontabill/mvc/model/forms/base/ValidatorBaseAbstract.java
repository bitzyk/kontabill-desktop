package main.java.kontabill.mvc.model.forms.base;

import main.java.kontabill.layout.elements.inputs.FormElement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
abstract public class ValidatorBaseAbstract implements Validator {

    List<String> errors = new ArrayList<>();

    private ValidatorConfig validatorConfig;

    private FormElement formElement;

    public ValidatorBaseAbstract(ValidatorConfig validatorConfig, FormElement formElement)
    {
        this.validatorConfig = validatorConfig;
        this.formElement = formElement;

        parseValidatorConfiguration();
    }

    protected abstract void parseValidatorConfiguration();

    protected ValidatorConfig getValidatorConfig() {
        return validatorConfig;
    }

    @Override
    public List<String> getErrors()
    {
        return errors;
    }

    protected void addError(String error)
    {
        errors.add(error) ;
    }

    protected void clearErrors()
    {
        errors.clear();;
    }

    public FormElement getFormElement() {
        return formElement;
    }
}
