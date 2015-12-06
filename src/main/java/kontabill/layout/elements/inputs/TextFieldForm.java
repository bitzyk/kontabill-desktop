package main.java.kontabill.layout.elements.inputs;


import main.java.kontabill.layout.elements.UIElement;
import main.java.kontabill.mvc.model.forms.base.ValidableElement;

import javax.swing.*;
import java.awt.*;

public class TextFieldForm extends JTextField implements UIElement, FormElement
{
    private String label;

    public TextFieldForm() {
        super(20);
        init();
    }

    @Override
    public void init()
    {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#d2d2d2"), 1),
                BorderFactory.createEmptyBorder(4, 5, 4, 5)
        ));
    }

    @Override
    public String getValue() {
        return getText();
    }


    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public FormElement setLabel(String label) {
        this.label = label;
        return this;
    }
}
