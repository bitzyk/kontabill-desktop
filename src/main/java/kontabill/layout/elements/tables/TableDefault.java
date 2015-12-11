package main.java.kontabill.layout.elements.tables;

import main.java.kontabill.Kontabill;
import main.java.kontabill.mvc.model.entities.table_models.BaseAbstract;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 */
public class TableDefault extends JTable {


    private BaseAbstract dataModel;

    /**
     * On setModel we add mouselistener on checkbox table header
     * @param dataModel
     */
    public void setModel(BaseAbstract dataModel) {
        super.setModel(dataModel);
        this.dataModel = dataModel;

        initAfterSetModel();
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
    {
        Component c = super.prepareRenderer(renderer, row, column);

        BaseAbstract model = (BaseAbstract) getModel();

        // set background for row accordingly
            // if is a edited row -> edited background, added row -> added background, default otherwise
        if (model.getEditedRowsIndexes().contains(row)) {
            c.setBackground(TableRowsStyle.BACKGROUND_EDITED);
        } else if (model.getAddedRowsIndexes().contains(row)) {
            c.setBackground(TableRowsStyle.BACKGROUND_ADDED);
        } else {
            c.setBackground(TableRowsStyle.BACKGROUND_DEFAULT);
        }

        return c;
    }

    private void initAfterSetModel()
    {
        addMouseListenerCheckboxTableHeaderColumn();
        setTableColumnsWidth();
        setColumnsAlignement();
    }

    /**
     * Set table columns width bsaed on the configuration
     */
    private void setTableColumnsWidth()
    {
        int[] columnsPercentageWidth = dataModel.getColumnsPercentageWidth();

        for (int i = 0; i < columnsPercentageWidth.length; i++) {
            int widthColumn = columnsPercentageWidth[i] * 1000;
            getColumnModel().getColumn(i).setPreferredWidth(widthColumn);
        }
    }

    private void setColumnsAlignement()
    {
        int[] columnsAlignement = dataModel.getColumnsDefinition().getColumnsAlignement();
        for (int i = 0; i < columnsAlignement.length; i++) {
            if(i == dataModel.getCheckedColumnIndex()) {
                continue;
            }
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(columnsAlignement[i]);
            getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void addMouseListenerCheckboxTableHeaderColumn()
    {
        getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int clickedAtColumnIndex = getTableHeader().columnAtPoint(e.getPoint());
                BaseAbstract model = (BaseAbstract)getModel();

                if(clickedAtColumnIndex == model.getCheckedColumnIndex()) {
                    // the new state of checkbox wil be the oposite of the current one
                    Boolean newCheckState = ! model.getStateGlobalChecked();

                    for (int i = 0; i < model.getCheckedEntitiesArray().length; i++) {
                        model.setValueAt(newCheckState, i, model.getCheckedColumnIndex());
                    }

                    // set state global checked after checked all rows with new state
                    model.setStateGlobalChecked(newCheckState);
                }
            }
        });
    }

    @Override
    protected JTableHeader createDefaultTableHeader() {
        return new JTableHeader(getColumnModel()) {
            /**
             * add tooltip behaviour
             */
            @Override
            public String getToolTipText(MouseEvent event) {

                int indexColumn = getColumnModel().getColumnIndexAtX(event.getPoint().x);
                return dataModel.getTooltipAtColumnIndex(indexColumn);

            }

        };
    }

}
