package main.java.kontabill.mvc.model.entities.table_models;

import main.java.kontabill.mvc.model.entities.LegalEntityDetailPerson;
import main.java.kontabill.mvc.model.entities.Representative;
import main.java.kontabill.mvc.model.entities.table_models.base.BaseAbstract;

import java.util.HashMap;

/**
 *
 */
public class RepresentativeTableModel extends BaseAbstract {

    /**
     * Column definition
     *  - table header lable
     *  - tooltip info (speicify empty string if no info is required)
     *  - percentage of width (total 100 for all columns)
     *  - alignement
     */
    private static String[][] COLUMN_DEFINITION = {
            {
                    "Nr.",
                    null,
                    "5",
                    "center",
            },
            {
                    "Nume",
                    null,
                    "40",
                    "left",
            },
            {
                    "Cnp",
                    null,
                    "20",
                    "left",
            },
            {
                    "✓ Selecteaza",
                    "Selecteaza/deselecteaza toate randurile",
                    "15",
                    "left",
            },
    };

    private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {
            String.class,
            String.class,
            String.class,
            Boolean.class,
    };

    public RepresentativeTableModel(HashMap mapEntitiesCollection) {
        super(mapEntitiesCollection, COLUMN_DEFINITION, COLUMN_TYPES);
    }

    protected static final int CHECKED_COLUMN_INDEX = 3;


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Representative representative = (Representative)listEntitiesCollection.get(rowIndex);

        Object value = null;

        switch (columnIndex) {
            case 0:
                value = rowIndex + 1;
                break;
            case 1:
                value = representative.getName();
                break;
            case 2:
                value = representative.getIdentifier();
                break;
            case CHECKED_COLUMN_INDEX:
                value = checkedEntitiesArray[rowIndex];
                break;
        }

        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (columnIndex == CHECKED_COLUMN_INDEX) {
            fireTableCellUpdated(rowIndex, columnIndex);
            checkedEntitiesArray[rowIndex] = (Boolean)aValue;
        }
    }

    @Override
    public int getCheckedColumnIndex() {
        return CHECKED_COLUMN_INDEX;
    }

}
