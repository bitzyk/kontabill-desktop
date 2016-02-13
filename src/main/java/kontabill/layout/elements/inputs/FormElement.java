package main.java.kontabill.layout.elements.inputs;

import main.java.kontabill.mvc.model.forms.base.ValidableElement;
import main.java.kontabill.mvc.model.forms.base.Validator;

import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 */
public interface FormElement extends ValidableElement {

    public String getLabel();

    public FormElement setLabel(String label);

    public void setValue(String value);

    public void addActionListener(ActionListener actionListener);

    public List<Validator> getValidators();

    public void setValidators(List<Validator> validators);

}
