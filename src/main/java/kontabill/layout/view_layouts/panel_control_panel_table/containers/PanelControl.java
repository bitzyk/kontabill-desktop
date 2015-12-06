package main.java.kontabill.layout.view_layouts.panel_control_panel_table.containers;

import main.java.kontabill.layout.ViewUtils;
import main.java.kontabill.layout.elements.factories.ButtonFactory;
import main.java.kontabill.layout.menu.MenuItemListener;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.model.RowTypePanels;
import main.java.kontabill.mvc.view.BaseAbstractView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class PanelControl extends BasePanelAbstract {

    protected void init()
    {
        setBackground(Color.decode("#eeeeee"));
        setBorder(BorderFactory.createLineBorder(Color.decode("#d4d4d4")));
        setLayout(new GridBagLayout());
    }

    @Override
    protected void addRowPanelAndConstraints(JPanel panel, RowTypePanels rowType) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = rowY;
        gbc.weightx = 1;
        gbc.insets = getRowInsets(new Insets(10, 10, 5, 10));

        if(rowType == RowTypePanels.DEFAULT) {
            setDefaultRowPanelConstraints(panel, gbc);
        } else if (rowType == RowTypePanels.FORM) {
            setFormRowPanelConstraints(panel, gbc);
        }

        // add panel to panelControl
        add(panel, gbc);
    }

    /**
     * Set layout and add new constraint on gbc value reference for the new row represented by panel value references.
     *
     * @param panel
     * @param gbc
     */
    private void setDefaultRowPanelConstraints(JPanel panel, GridBagConstraints gbc)
    {
        gbc.anchor = GridBagConstraints.WEST;

        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
    }
    /**
     * Set layout and add new constraint on gbc value reference for the new row represented by panel value references.
     *
     * @param panel
     * @param gbc
     */
    private void setFormRowPanelConstraints(JPanel panel, GridBagConstraints gbc)
    {
        gbc.fill = GridBagConstraints.BOTH;
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(7, 15, 7, 15));

        MigLayout migLayout = new MigLayout();
        panel.setLayout(migLayout);
    }

    public void addSubmenusButtonToPanel(JPanel panel, BaseAbstractView view)
    {
        String[][] submenus = main.java.kontabill.mvc.model.utils.Menu.getSubmenuforMenuController(
                view.getControllerForView().getControllerName()
        );

        for (String[] submenu : submenus) {

            Boolean submenuIsActive = false;
            if(submenu[2].equals(view.getMvc().getActionControllerCurrent())) {
                submenuIsActive = true;
            }

            JButton button = ButtonFactory.createButtonGrayControlPanel(submenu[1], submenuIsActive);
            ViewUtils.addComponentPadding(
                    button,
                    panel
            );
            button.setActionCommand(main.java.kontabill.mvc.model.utils.Menu.getMenuActionCommand(
                    submenu[0], submenu[1], view.getControllerForView().getControllerName(), submenu[2]
            ));
            button.addActionListener(new MenuItemListener());
        }
    }


}
