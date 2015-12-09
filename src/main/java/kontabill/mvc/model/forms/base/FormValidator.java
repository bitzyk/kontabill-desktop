package main.java.kontabill.mvc.model.forms.base;

import main.java.kontabill.layout.elements.inputs.FormElement;

import java.util.*;

/**
 *
 */
public class FormValidator {


    private Map<String, List<Validator>> validators = new HashMap<>();

    private Map<String, List<String>> validationErrors = new HashMap<>();

    private BaseAbstractForm form;

    public FormValidator(BaseAbstractForm form)
    {
        this.form = form;
    }

    private boolean lastFormValidationValid = false;

    public void addValidatorToElement(String key, Validator validator)
    {

    }

    public void setValidatorsToElement(String key, List<ValidatorConfig> validatorConfigs)
    {
        List<Validator> validatorsList = new ArrayList<>();

        for (ValidatorConfig validatorConfig:validatorConfigs) {
            // call factory to instantiate validator
            Validator validator = ValidatorFactory.createValidator(
                    validatorConfig,
                    form.getFormElements().get(key)
            );
            validatorsList.add(validator);

        }

        this.validators.put(key, validatorsList);
    }

    /**
     * Validate
     *  - if only one validator of one element is invalid the entire form will not be valid
     * @return
     */
    public Boolean validateForm()
    {
        Boolean validForm = true;

        Map<String, FormElement> formElements = form.getFormElements();

        Set<String> keys = formElements.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            FormElement formElement = formElements.get(key);
            boolean elementIsValid = validateElement(key, formElement); // validate element

            if (elementIsValid == false) {
                validForm = false;
            }
        }

        // set lastFormValidationValid property
        this.lastFormValidationValid = validForm;

        return validForm;
    }

    /**
     * Validate
     *  - if only one validator of cur element is invalid the entire element will not be valid
     * @param key
     * @param formElement
     * @return
     */
    public boolean validateElement(String key, FormElement formElement)
    {
        boolean valid = true;

        // clear previous errors for this field
        clearElementValidationErrors(key);

        // iterate in element validators if it has
        List<Validator> elementValidators = getElementValidators(key);

        // call validate for every validator of the element
        for (Validator validator:elementValidators) {
            boolean validatorValid = validator.validate(formElement.getValue());
            if (validatorValid == false) {
                valid = false;

                addElementValidationErrors(key, validator.getErrors());
            }
        }

        return valid;
    }

    private void addElementValidationErrors(String key, List<String> errors)
    {
        List<String> existingErrors = getValidationElementErrors(key);

        for (String error:errors) {
            existingErrors.add(error);
        }
    }

    protected List<Validator> getElementValidators(String key)
    {
        List<Validator> validators = this.validators.get(key);

        if ( ! (validators instanceof List) ) {
            validators = new ArrayList<Validator>();
        }

        return  validators;
    }


    public List<String> getValidationElementErrors(String key)
    {
        List<String> elementErrors = validationErrors.get(key);

        return  elementErrors;
    }

    public void clearElementValidationErrors(String key)
    {
        // clear by rewritting with an empty list
        validationErrors.put(key, new ArrayList<String>());
    }

    public void clearAllValidationErrors()
    {

    }

    public boolean isLastFormValidationValid() {
        return lastFormValidationValid;
    }
}
