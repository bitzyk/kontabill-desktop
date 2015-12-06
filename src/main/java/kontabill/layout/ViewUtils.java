package main.java.kontabill.layout;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class ViewUtils {


    /**
     * Method for adding padding close to the component
     *  - it adds the component and next to it a rigid space
     * @param component
     * @param panel
     */
    public static void addComponentPadding(JComponent component, JPanel panel)
    {
        panel.add(component);
        panel.add(Box.createRigidArea(new Dimension(5,0)));
    }
}
