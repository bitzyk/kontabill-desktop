package main.java.kontabill.layout;

import main.java.config.MenuConfig;
import main.java.kontabill.Kontabill;
import main.java.kontabill.layout.containers.ControllerJPanel;
import main.java.kontabill.layout.containers.FrameMain;
import main.java.kontabill.layout.containers.WidgetsJPanel;
import main.java.kontabill.layout.menu.MenuJMenu;
import main.java.kontabill.layout.menu.MenuJMenuBar;
import main.java.kontabill.layout.menu.MenuJMenuItem;
import main.java.kontabill.mvc.model.utils.*;

import javax.swing.*;
import java.awt.*;


/**
 *
 */
public class Layout {

    /**
     * Represent kontabill instance app.
     * Can be used to build layout components according with kontabill instance object state.
     */
    private Kontabill kontabill;

    private FrameMain frame;

    private MenuJMenuBar menuBar;

    private ControllerJPanel controllerPanel;

    private WidgetsJPanel widgetsPanel;

    public Layout(Kontabill kontabill)
    {
        this.kontabill = kontabill;
    }

    /**
     * Run the Layout
     * Trigger in chain when Kontabill.run() is called
     */
    public void run()
    {
        setFrame();
        createBackbone();

        frame.setVisible(true);
    }

    /**
     * Set the main frame of the app
     */
    private void setFrame()
    {
        frame = new FrameMain("Kontabill app"); // @todo - should be set based on a configuration
    }

    /**
     * Create backbone of the frame
     */
    private void createBackbone()
    {
        // create menu
        createMenu();
        frame.setJMenuBar(menuBar);

        // create widgets panel
        createWidgetsPanel();

        // create controlle panel
        createControllerPanel();

        // add split for widgets panel and controller panel
        JSplitPane controllerWidgetsSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                widgetsPanel,
                controllerPanel
        );
        // set widgets part to be 25% of the frame width
        controllerWidgetsSplitPane.setDividerLocation((int)(frame.getWidthF()/4));

        frame.add(controllerWidgetsSplitPane);
    }

    private void createMenu()
    {
        menuBar = new MenuJMenuBar();

        for (int i = 0; i < MenuConfig.menu.length ; i++) {
            String menuLabel = MenuConfig.menu[i][1];

            String[][] submenus = null;

            try {
                submenus = MenuConfig.submenu[i];
            } catch (ArrayIndexOutOfBoundsException e) {}

            if (submenus != null && submenus.length != 0) {
                Boolean menuHasSubmenu = submenus[0].length != 0;

                if(menuHasSubmenu) {
                    MenuJMenu jMenu = new MenuJMenu(menuLabel);

                    for (int j = 0; j < submenus.length; j++) {
                        String submenuLabel = submenus[j][1];

                        MenuJMenuItem submenuItem = new MenuJMenuItem(submenuLabel, kontabill);
                        submenuItem.setActionCommand(
                                main.java.kontabill.mvc.model.utils.Menu.getMenuActionCommand( submenus[j][0], submenus[j][1], MenuConfig.menu[i][2], submenus[j][2] )
                        );

                        jMenu.add(submenuItem);
                    }

                    menuBar.add(jMenu);
                }
                else {
                    MenuJMenuItem menuItem = new MenuJMenuItem(menuLabel, kontabill);
                    menuItem.setMaximumSize(new Dimension(60, 80));
                    menuItem.setActionCommand(MenuConfig.menu[i][0] + "|" + MenuConfig.menu[i][1] + "|" + MenuConfig.menu[i][2] + "|" + "indexAction");

                    menuBar.add(menuItem);
                }
            }
        }
    }

    private void createWidgetsPanel()
    {
        widgetsPanel = new WidgetsJPanel(kontabill);
        widgetsPanel.setBackground(Color.WHITE);
    }

    private void createControllerPanel()
    {
        controllerPanel = new ControllerJPanel(kontabill);
    }

    public FrameMain getFrame()
    {
        return frame;
    }

    /**
     * Return Controller panel instance
     *
     * @return
     */
    public ControllerJPanel getControllerPanel() {
        return controllerPanel;
    }
}
