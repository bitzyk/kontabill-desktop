package main.java.kontabill.layout.view_layouts.panel_control_panel_table.containers;

import main.java.kontabill.layout.elements.tables.TableDefault;
import main.java.kontabill.layout.view_layouts.panel_control_panel_table.model.RowTypePanels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cbitoi on 19/11/15.
 */
public class PanelTable extends BasePanelAbstract {


    protected void init()
    {
        removeAll();
        repaint();
        validate();

        setBackground(Color.WHITE);

        setLayout(new GridBagLayout());

        addRowLineSeparator();
    }

    protected void addRowPanelAndConstraints(JPanel panel, RowTypePanels rowType)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = rowY;
        gbc.weightx = 1;
        gbc.insets = getRowInsets(new Insets(10, 0, 5, 0));


        if (rowType == RowTypePanels.TABLE) {
            setTableRowPanelConstraints(panel, gbc);
        } else if (rowType == RowTypePanels.DEFAULT) {
            setDefaultRowPanelConstraints(panel, gbc);
        } else if (rowType == RowTypePanels.LINE) {
            setLineRowPanelConstraints(panel, gbc);
        }

        panel.setBackground(Color.WHITE);

        // add panel to panelControl
        add(panel, gbc);
    }

    private void setTableRowPanelConstraints(JPanel panel, GridBagConstraints gbc)
    {
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        panel.setLayout(new GridBagLayout());
    }

    private void setDefaultRowPanelConstraints(JPanel panel, GridBagConstraints gbc)
    {
        gbc.anchor = GridBagConstraints.EAST; // align east

        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
    }

    private void setLineRowPanelConstraints(JPanel panel, GridBagConstraints gbc)
    {
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.setPreferredSize(new Dimension(0, 1)); // set only height because is line separator
    }


    public TableDefault addTable(JPanel tablePanel)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        TableDefault table = new TableDefault();
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, gbc);

        // add background to the rest of scroll pane (if any)
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#aaaaaa"), 1));

        return table;
    }

    private void addRowLineSeparator()
    {
        JPanel panel = addRowPanel(RowTypePanels.LINE);
        panel.setBorder(BorderFactory.createLineBorder(Color.decode("#cccccc"), 1));
    }


}