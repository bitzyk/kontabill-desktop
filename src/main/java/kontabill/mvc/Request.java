package main.java.kontabill.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Request {


    /**
     * By default reapaint components
     */
    private Boolean repaintComponents = true;

    private Map<String, Object> dataPayload = new HashMap<>();

    public void addDataItem(String key, Object data)
    {
        dataPayload.put(key, data);
    }

    public Object getDataItem(String key)
    {
        Object data = dataPayload.get(key);
        return data;
    }

    public Boolean hasDataItem(String key)
    {
        if(getDataItem(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void resetDataItems()
    {
        dataPayload.clear();
    }

    public void setRepaintComponents(Boolean repaintComponents) {
        this.repaintComponents = repaintComponents;
    }

    public Boolean getRepaintComponents() {
        return repaintComponents;
    }
}
