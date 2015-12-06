package main.java.kontabill.layout.menu;

import main.java.kontabill.Kontabill;
import main.java.kontabill.mvc.Request;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class MenuItemListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        String[] actionCommand = e.getActionCommand().split("\\|");

        String menuIdentifier = actionCommand[0];
        String menuLabel = actionCommand[1];
        String controllerIdentifier = actionCommand[2];
        String actionIdentifier = actionCommand[3];


        Kontabill.getInstance().getMVC().setController(controllerIdentifier);
        Kontabill.getInstance().getMVC().runController(actionIdentifier, new Request());
    }
}