package main.java.kontabill.layout.view_layouts.panel_control_panel_table.containers;

import main.java.kontabill.layout.view_layouts.panel_control_panel_table.model.RowTypePanels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 */
abstract public class BasePanelAbstract extends JPanel {

    protected int rowY;

    protected java.util.List<JPanel> rowsPanel = new ArrayList<>();

    public BasePanelAbstract() {
        super();
        init();
    }

    protected abstract void init();

    protected abstract void addRowPanelAndConstraints(JPanel panel, RowTypePanels rowType);



    /**
     * Methods for adding new rows in panel control
     * @param rowType
     * @return
     */
    public JPanel addRowPanel(RowTypePanels rowType)
    {
        JPanel panel = new JPanel();

        // add constraints and layout based on row type
        addRowPanelAndConstraints(panel, rowType);

        // add panel row to rows panel variable
        rowsPanel.add(panel);
        rowY++;

        return panel;
    }

    protected Insets getRowInsets(Insets insets)
    {
        Insets newInsets = null;

        if (rowsPanel.size() == 0) {
            return insets;
        }
        else {
            newInsets = new Insets(insets.bottom, insets.left, insets.bottom + insets.bottom, insets.right);
        }

        // when are more or equal rows already added, then normalize the inset of previous row (e.g. when the third row
        // is added normalize the second row => less 5 px)
        if(rowsPanel.size() >= 2) {
            JPanel panel = rowsPanel.get(rowsPanel.size()-1);

            GridBagLayout layout = (GridBagLayout)getLayout();
            GridBagConstraints gbc = layout.getConstraints(panel);
            gbc.insets = new Insets(insets.bottom, insets.left, insets.bottom, insets.right);
            add(panel, gbc);
        }

        return newInsets;
    }
}
