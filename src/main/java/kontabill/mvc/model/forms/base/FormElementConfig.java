package main.java.kontabill.mvc.model.forms.base;

import main.java.kontabill.layout.elements.forms.model.InputType;

import java.util.*;

/**
 *
 */
public class FormElementConfig {

    private Map<String, ElementConfig> elementConfigMap =  new LinkedHashMap<>(); // for mantaing the order

    private String[][] initialElementsDefinition;

    public FormElementConfig(String[][] initialElementsDefinition) {
        this.initialElementsDefinition = initialElementsDefinition;

        init();
    }

    private void init()
    {
        for (int i = 0; i < initialElementsDefinition.length; i++) {
            String[] elementInitialConfig = initialElementsDefinition[i];

            ElementConfig elementConfig = new ElementConfig()
                    .setInputKey(elementInitialConfig[BaseAbstractForm.ELEMENT_DEFINITION_KEY_KEYID])
                    .setInputType(
                            InputType.valueOf(
                                    elementInitialConfig[BaseAbstractForm.ELEMENT_DEFINITION_KEY_INPUT_TYPE]
                            )
                    )
                    .setInputLabel(elementInitialConfig[BaseAbstractForm.ELEMENT_DEFINITION_KEY_LABEL])
                    .setGroupKey(elementInitialConfig[BaseAbstractForm.ELEMENT_DEFINITION_GROUP_KEYID]);

            elementConfigMap.put(
                    elementConfig.getInputKey(),
                    elementConfig
            );
        }
    }

    /**
     * Method for getting allformKeys as an array for the specified groupKey, for the current form
     * @param groupKey
     */
    public List<String> getGroupedFormKeys(String groupKey)
    {
        List<String> grouped = new ArrayList<>();

        for (int i = 0; i < initialElementsDefinition.length; i++) {
            // if groupKey is equal with element groupKey
            String currentElmGroupKeyDefinition = initialElementsDefinition[i][BaseAbstractForm.ELEMENT_DEFINITION_GROUP_KEYID];
            String currentElmKeyId = initialElementsDefinition[i][BaseAbstractForm.ELEMENT_DEFINITION_KEY_KEYID];

            if(currentElmGroupKeyDefinition.contains(groupKey)) {
                // add keyId for formElement
                grouped.add(currentElmKeyId);
            }
        }

        return grouped;
    }

    /**
     * @todo implement this
     * @param key
     * @return
     */
    public String getLabelOfElement(String key)
    {
        String label = "ion";

        return label;
    }

    /**
     * @todo implement this
     * @param key
     */
    public void getTypeOfElement(String key)
    {

    }

    public Map<String, ElementConfig> getElementConfigMap() {
        return elementConfigMap;
    }


    /**
     * Return all form keys as an array
     * @return
     */
    public String[] getAllFormKeys()
    {
        String[] allFormKeys = new String[elementConfigMap.size()];

        Iterator<String> iterator = elementConfigMap.keySet().iterator();

        int i = 0;
        while (iterator.hasNext()) {
            ElementConfig elementConfig = elementConfigMap.get(
                    iterator.next()
            );

            allFormKeys[i] = elementConfig.getInputKey();
            i++;
        }

        return allFormKeys;
    }
}
