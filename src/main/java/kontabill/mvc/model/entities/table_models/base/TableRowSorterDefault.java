package main.java.kontabill.mvc.model.entities.table_models.base;

import javax.swing.table.TableRowSorter;

/**
 *
 */
public class TableRowSorterDefault<M> extends TableRowSorter {

    TableRowFilterDefault<BaseAbstract, Object> rowFilter;

    public TableRowSorterDefault(BaseAbstract model) {
         super(model);
        setRowFilter();
    }

    private void setRowFilter()
    {
        rowFilter = new TableRowFilterDefault<BaseAbstract, Object>();

        super.setRowFilter(rowFilter);
    }

    @Override
    public TableRowFilterDefault<BaseAbstract, Object> getRowFilter() {
        return rowFilter;
    }
}
