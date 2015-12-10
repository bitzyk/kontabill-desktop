package main.java.kontabill.lib.core.request;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Request implements RequestPayloadContainer {


    private Map<String, Object> dataPayload = new HashMap<>();

    private RequestSessionPayload sessionPayload = new RequestSessionPayload();

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

    public void removeDataItem(String key)
    {
        dataPayload.remove(key);
    }

    public void resetDataItems()
    {
        dataPayload.clear();
    }

    public RequestSessionPayload getSessionPayload() {
        return sessionPayload;
    }
}
