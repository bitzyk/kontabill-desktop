package main.java.kontabill.mvc.model.utils;

import main.java.config.MenuConfig;

/**
 *
 */
public class Menu {


    public static String[][] getSubmenuforMenuController(String controllerIdentifier)
    {
        String[][] submenus = null;

        for (int i = 0; i < MenuConfig.menu.length ; i++) {
            if (controllerIdentifier.equals(MenuConfig.menu[i][2])) {

                submenus = MenuConfig.submenu[i];
                break;
            }
        }

        return submenus;
    }

    public static String getMenuActionCommand(String menuIdentifier, String menuLabel, String controllerIdentifier, String actionIdentifier)
    {
        String actionCommand = menuIdentifier + "|" + menuLabel + "|" + controllerIdentifier + "|" + actionIdentifier;

        return actionCommand;
    }

}
