package main.java.kontabill.mvc.model.forms.base;

import main.java.kontabill.layout.elements.factories.FormElementFactory;
import main.java.kontabill.layout.elements.inputs.FormElement;
import main.java.kontabill.lib.core.functional_interfaces.BlockRunner;
import main.java.kontabill.mvc.model.entities.Entity;

import javax.swing.*;
import java.util.*;

/**
 *
 */
abstract public class BaseAbstractForm {

    private Map<String, FormElement> formElements = new HashMap<>();

    private FormValidator formValidator;

    private FormElementConfig formElementConfig;

    private JButton submitButton;

    private BlockRunner submitBlockRunner;

    /**
     * variable to store dynamic change events configuration
     */
    protected Map<String, DynamicChangeConfig> dynamicChangeConfigMap = new HashMap<>();

    public static final int ELEMENT_DEFINITION_KEY_INPUT_TYPE = 0;
    public static final int ELEMENT_DEFINITION_KEY_LABEL = 1;
    public static final int ELEMENT_DEFINITION_KEY_KEYID = 2;
    public static final int ELEMENT_DEFINITION_GROUP_KEYID = 3;


    public BaseAbstractForm() {
        initForm();
    }

    private void initForm()
    {
        initFormElementConfig();
        initFormValidator();
        initInputElements();
        initInitialInputFocus();
        initInputValidators();
    }

    private void initFormElementConfig()
    {
        this.formElementConfig = new FormElementConfig(
                getElementsDefinition()
        );
    }

    private void initFormValidator()
    {
        this.formValidator = new FormValidator(this);
    }


    private void initInputElements()
    {
        Map<String, ElementConfig> elementConfigMap = formElementConfig.getElementConfigMap();

        Set<String> keys = elementConfigMap.keySet();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()) {
            ElementConfig elementConfig = elementConfigMap.get(iterator.next());
            initInputElement(elementConfig);
        }

    }

    private void initInitialInputFocus()
    {
        JComponent textField =  ((JComponent)getFormElement(getElementsDefinition()[0][BaseAbstractForm.ELEMENT_DEFINITION_KEY_KEYID]));

        SwingUtilities.invokeLater( new Runnable() {

            public void run() {
                textField.requestFocusInWindow();
            }
        } );
    }


    private void initInputElement(ElementConfig elementConfig)
    {
        FormElement input = FormElementFactory.createFormElement(
                elementConfig
        );

        formElements.put(elementConfig.getInputKey(), input);
    }

    private void initInputValidators()
    {
        String[][] elementsDefinition = getElementsDefinition();
        Map<String, List<ValidatorConfig>> elementsValidators = getElementsValidatorsConfig();

        for (int i = 0; i < elementsDefinition.length; i++) {
            String elementKeyId = elementsDefinition[i][ELEMENT_DEFINITION_KEY_KEYID];

            List<ValidatorConfig> validatorConfigs = elementsValidators.get(elementKeyId);

            // if cur element has no validators continue
            if(! (validatorConfigs instanceof List)) {
                continue;
            }

            // add validators to element -> delegate to form validator
            getFormValidator().setValidatorsForElement(elementKeyId, validatorConfigs);
        }

    }

    public void registerSubmitButton(JButton submitButton, BlockRunner submitBlockRunner)
    {
        this.submitButton = submitButton;
        this.submitBlockRunner = submitBlockRunner;

        // attach action listener to submit button
        this.submitButton.addActionListener(e -> {
            submitBlockRunner.run();
        });
    }


    public boolean validate()
    {
        return formValidator.validateForm();
    }

    public abstract String[][] getElementsDefinition();

    protected abstract Map<String, List<ValidatorConfig>> getElementsValidatorsConfig();

    public abstract void hydrateEntity(Entity entity);

    public abstract void  hydrateForm(Entity entity);

    public FormValidator getFormValidator() {
        return formValidator;
    }

    public Map<String, FormElement> getFormElements() {
        return formElements;
    }

    public FormElement getFormElement(String key)
    {
        FormElement formElement = formElements.get(key);

        if(! (formElement instanceof FormElement)) {
            throw new RuntimeException("Element for key " + key + " is not a valid form element.");
        }

        return formElement;
    }

    /**
     * Get form key for speciied formElement
     * @param formElement
     * @return
     */
    public String getFormKey(FormElement formElement)
    {
        String keyFormElement = "";

        Set set = formElements.keySet();
        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            FormElement formElementCurrent = formElements.get(key);

            if(formElement == formElementCurrent) {
                keyFormElement = key;
                break;
            }
        }

        if(keyFormElement.length() == 0) {
            throw new RuntimeException("Form key for the specified formElement does not exist.");
        }

        return keyFormElement;
    }

    public boolean formElementBelongToThisForm(FormElement formElement)
    {
        boolean belong = false;

        Set set = formElements.keySet();
        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {
            FormElement testAgainst = formElements.get(iterator.next());

            if(formElement == testAgainst) {
                belong = true;
                break;
            }
        }

        return belong;
    }

    public Map<String, DynamicChangeConfig> getDynamicChangeConfigMap() {
        return dynamicChangeConfigMap;
    }

    public BlockRunner getSubmitBlockRunner() {
        return submitBlockRunner;
    }


    public FormElementConfig getFormElementConfig() {
        return formElementConfig;
    }
}
