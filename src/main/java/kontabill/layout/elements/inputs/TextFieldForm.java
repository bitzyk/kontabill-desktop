package main.java.kontabill.layout.elements.inputs;


import main.java.kontabill.layout.elements.UIElement;
import main.java.kontabill.mvc.model.forms.base.ValidableElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextFieldForm extends JTextField implements UIElement, FormElement
{
    private String label;

    private Color colorFocusGained = Color.decode("#f4fade");
    private Color colorFocusLost = Color.decode("#ffffff");

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

        // add focus listener
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(colorFocusGained);
            }
            @Override
            public void focusLost(FocusEvent e) {
                setBackground(colorFocusLost);
            }
        });
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
