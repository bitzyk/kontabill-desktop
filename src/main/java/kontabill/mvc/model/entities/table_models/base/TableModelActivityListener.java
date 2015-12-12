package main.java.kontabill.mvc.model.entities.table_models.base;

import main.java.kontabill.lib.core.request.Request;
import main.java.kontabill.lib.core.request.RequestSessionKey;

/**
 *
 */
public class TableModelActivityListener {

    private BaseAbstract tableBaseAbstract;

    private Request request;


    public TableModelActivityListener(BaseAbstract tableBaseAbstract, Request request)
    {
        this.tableBaseAbstract = tableBaseAbstract;
        this.request = request;
    }

    public void listenActivity()
    {
        listenActivityRequestSession();
    }

    private void listenActivityRequestSession()
    {
        if(request.getSessionPayload().hasDataItem(RequestSessionKey.EDITED_KEY)) {
            int rowEdited = tableBaseAbstract.getRowAtEntityId(
                    (int) request.getSessionPayload().getDataItem(RequestSessionKey.EDITED_KEY)
            );
            tableBaseAbstract.getEditedRowsIndexes().add(rowEdited);
            tableBaseAbstract.fireTableRowsUpdated(rowEdited, rowEdited);
        }

        if(request.getSessionPayload().hasDataItem(RequestSessionKey.ADDED_KEY)) {
            int rowAdded = tableBaseAbstract.getRowAtEntityId(
                    (int) request.getSessionPayload().getDataItem(RequestSessionKey.ADDED_KEY)
            );
            tableBaseAbstract.getAddedRowsIndexes().add(rowAdded);
            tableBaseAbstract.fireTableRowsUpdated(rowAdded, rowAdded);
        }
    }


}
