package main.java.kontabill.layout.view_layouts.panel_control_panel_table;

import main.java.kontabill.layout.view_layouts.panel_control_panel_table.containers.PanelControl;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.containers.PanelTable;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class ViewLayout {

    private main.java.kontabill.layout.Layout layout;

    private PanelControl panelControl;

    private PanelTable panelTable;

    public ViewLayout(main.java.kontabill.layout.Layout layout) {
        this.layout = layout;
        setBackbone();
    }

    private void setBackbone()
    {
        panelControl = new PanelControl();

        panelTable = new PanelTable();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.weightx = 1;

        layout.getControllerPanel().add(panelControl, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1;
        layout.getControllerPanel().add(panelTable, gbc);
    }

    public PanelControl getPanelControl() {
        return panelControl;
    }

    public PanelTable getPanelTable() {
        return panelTable;
    }
}
