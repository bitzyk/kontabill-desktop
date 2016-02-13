package main.java.kontabill.mvc.model.forms.base;

import main.java.kontabill.layout.elements.forms.model.InputType;

/**
 *
 */
public class ElementConfig {

    private InputType inputType;

    private String inputLabel;

    private String inputKey;

    private String groupKey;


    public InputType getInputType() {
        return inputType;
    }

    public ElementConfig setInputType(InputType inputType) {
        this.inputType = inputType;
        return this;
    }

    public String getInputLabel() {
        return inputLabel;
    }

    public ElementConfig setInputLabel(String inputLabel) {
        this.inputLabel = inputLabel;
        return this;
    }

    public String getInputKey() {
        return inputKey;
    }

    public ElementConfig setInputKey(String inputKey) {
        this.inputKey = inputKey;
        return this;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public ElementConfig setGroupKey(String groupKey) {
        this.groupKey = groupKey;
        return this;
    }
}
