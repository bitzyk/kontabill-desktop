package main.java.kontabill.mvc.model.entities.table_models;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public abstract class BaseAbstract extends AbstractTableModel {

    protected HashMap<Object, Object> mapEntitiesCollection;

    protected List<Object> listEntitiesCollection;

    protected Boolean[] checkedEntitiesArray;

    private ColumnsDefinition columnsDefinition;


    protected Class<?>[] columnTypes;

    protected Boolean stateGlobalChecked = false;

    public BaseAbstract(HashMap mapEntitiesCollection, String[][] columnDefinition, Class[] columnTypes)
    {
        super();

        // set collections
        this.mapEntitiesCollection = mapEntitiesCollection;
        listEntitiesCollection = new ArrayList<>(mapEntitiesCollection.values());
        checkedEntitiesArray = new Boolean[listEntitiesCollection.size()];
        Arrays.fill(checkedEntitiesArray, false);


        columnsDefinition = new ColumnsDefinition(columnDefinition);

        this.columnTypes = columnTypes;
    }

    public int getRowCount()
    {
        return listEntitiesCollection.size();
    }

    public int getColumnCount()
    {
        return columnsDefinition.getColumnsCount();
    }

    public String getColumnName(int column)
    {
        return columnsDefinition.getColumnName(column);
    }


    public boolean isCellEditable(int row, int column)
    {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    public abstract int getCheckedColumnIndex();

    public void setStateGlobalChecked(Boolean stateGlobalChecked) {
        this.stateGlobalChecked = stateGlobalChecked;
    }

    public Boolean getStateGlobalChecked() {
        return stateGlobalChecked;
    }

    public Boolean[] getCheckedEntitiesArray() {
        return checkedEntitiesArray;
    }

    public void setCheckedEntitiesArray(Boolean[] checkedEntitiesArray) {
        this.checkedEntitiesArray = checkedEntitiesArray;
    }

    public String getTooltipAtColumnIndex(int columnIndex)
    {
        return columnsDefinition.getTooltipAtColumnIndex(columnIndex);
    }

    /**
     * Return columns percentage with based on column definition configuration
     *
     * @return
     */
    public int[] getColumnsPercentageWidth()
    {
        return columnsDefinition.getColumnsPercentageWidth();
    }

    public ColumnsDefinition getColumnsDefinition() {
        return columnsDefinition;
    }

    /**
     * Predicate of the model - return true if at least one row has been selected
     * @return
     */
    public Boolean hasSelectedRows()
    {
        Boolean hasSelectedRows = false;

        for (Boolean isChecked:checkedEntitiesArray) {
            if (isChecked == true) {
                hasSelectedRows = true;
                break;
            }
        }

        return hasSelectedRows;
    }

    public ArrayList getSelectedEntities()
    {
        ArrayList selectedEntities = new ArrayList();

        for (int i = 0; i < checkedEntitiesArray.length; i++) {
            // if checked is selected add entity to selectedEntities collection
            if(true == checkedEntitiesArray[i]) {
                selectedEntities.add(
                        listEntitiesCollection.get(i)
                );
            }
        }

        return selectedEntities;
    }

    public int countSelectedRows()
    {
        int selectedRows = 0;

        for (Boolean isChecked:checkedEntitiesArray) {
            if (isChecked == true) {
                selectedRows++;
            }
        }

        return selectedRows;
    }
}
