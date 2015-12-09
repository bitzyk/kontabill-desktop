package main.java.kontabill.layout.elements.separators;

import main.java.kontabill.layout.elements.UIElement;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class LineSeparator extends JPanel implements UIElement
{

    public LineSeparator() {
        super();
        init();
    }

    @Override
    public void init()
    {
        this.setBackground(Color.WHITE);

        JPanel panelBorder = new JPanel();

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.fill = GridBagConstraints.BOTH;

        // set only height because is line separator
        panelBorder.setPreferredSize(new Dimension(0, 1));

        panelBorder.setBorder(BorderFactory.createLineBorder(Color.decode("#cccccc"), 1));

        add(panelBorder, gbc);
    }
}
