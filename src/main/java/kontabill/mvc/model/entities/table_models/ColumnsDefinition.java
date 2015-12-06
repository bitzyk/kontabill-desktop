package main.java.kontabill.mvc.model.entities.table_models;

import javax.swing.*;

/**
 * Class that deals with computing values based on columnsDefinition configuration
 */
public class ColumnsDefinition {

    protected String[][] columnDefinition;

    protected static final int COLUMN_DEFINITION_NAME_INDEX = 0;

    protected static final int COLUMN_DEFINITION_TOOLTIP_INDEX = 1;

    protected static final int COLUMN_DEFINITION_WIDTH_INDEX = 2;

    protected static final int COLUMN_DEFINITION_ALIGNEMENT_INDEX = 3;

    private int[] columnsPercentageWidth;

    private int[] columnsAlignement;

    public ColumnsDefinition(String[][] columnDefinition) {
        this.columnDefinition = columnDefinition;

        computeState();
    }


    private void computeState()
    {
        columnsPercentageWidth = new int[columnDefinition.length];
        columnsAlignement = new int[columnDefinition.length];

        for (int i = 0; i < columnDefinition.length; i++) {
            columnsPercentageWidth[i] = Integer.parseInt(columnDefinition[i][COLUMN_DEFINITION_WIDTH_INDEX]);
            columnsAlignement[i] = alignementConfigToIntConstant(columnDefinition[i][COLUMN_DEFINITION_ALIGNEMENT_INDEX]);
        }
    }

    public int[] getColumnsPercentageWidth()
    {
        return columnsPercentageWidth;
    }

    public int[] getColumnsAlignement() {
        return columnsAlignement;
    }

    public String getColumnName(int column)
    {
        String colLabel = columnDefinition[column][COLUMN_DEFINITION_NAME_INDEX];

        return colLabel;
    }

    public int getColumnsCount()
    {
        return columnDefinition.length;
    }

    public String getTooltipAtColumnIndex(int columnIndex)
    {
        return columnDefinition[columnIndex][COLUMN_DEFINITION_TOOLTIP_INDEX];
    }


    private static int alignementConfigToIntConstant(String config)
    {
        int alignement;

        switch (config) {
            case "left":
                alignement = SwingConstants.LEFT;
                break;
            case "center":
                alignement = SwingConstants.CENTER;
                break;
            default:
                alignement = SwingConstants.LEFT;
                break;
        }

        return alignement;
    }
}
