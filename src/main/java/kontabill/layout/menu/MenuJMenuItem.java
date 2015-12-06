package main.java.kontabill.layout.menu;

import main.java.kontabill.Kontabill;

import javax.swing.*;
import java.util.*;

/**
 *
 */
public class MenuJMenuItem extends JMenuItem {

    /**
     * Represent kontabill instance app.
     */
    private Kontabill kontabill;

    public MenuJMenuItem(String text, Kontabill kontabill) {
        super(text);

        this.kontabill = kontabill;
        initJMenuItem();
    }

    /**
     * All the MenuJMenuItem represent submenu's that trigger controller action
     *  - if the controller selected is the same with the current one -> do not set the new one
     *  - if controller is not the same - set the new controller
     *
     *  - reset the action everytime -> will trigger repaint
     */
    private void initJMenuItem()
    {
        addActionListener(new MenuItemListener());
    }


}



