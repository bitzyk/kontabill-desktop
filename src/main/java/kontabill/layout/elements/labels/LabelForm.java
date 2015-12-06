package main.java.kontabill.layout.elements.labels;


import main.java.kontabill.layout.elements.UIElement;

import javax.swing.*;
import java.awt.*;

public class LabelForm extends JLabel implements UIElement
{
    public LabelForm(String text)
    {
        super(text);
        init();
    }

    @Override
    public void init()
    {
        setForeground(Color.BLACK);
        setBackground(Color.CYAN);
    }
}
