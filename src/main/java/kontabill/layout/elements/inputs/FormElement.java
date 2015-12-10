package main.java.kontabill.layout.elements.inputs;

import main.java.kontabill.mvc.model.forms.base.ValidableElement;

/**
 *
 */
public interface FormElement extends ValidableElement {

    public String getLabel();

    public FormElement setLabel(String label);

    public void setValue(String value);

}
