package main.java.kontabill.lib.core.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Request Payload class with session behaviour
 *  - when data is retrieved is is deleted from the payload
 *
 */
public class RequestSessionPayload implements RequestPayloadContainer {

    private Map<String, Object> dataPayload = new HashMap<>();


    @Override
    public void addDataItem(String key, Object data)
    {
        dataPayload.put(key, data);
    }

    @Override
    public Object getDataItem(String key)
    {
        Object data = dataPayload.get(key);
        removeDataItem(key); // session behaviour

        return data;
    }

    @Override
    public Boolean hasDataItem(String key)
    {
        if(dataPayload.get(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void removeDataItem(String key)
    {
        dataPayload.remove(key);
    }

    @Override
    public void resetDataItems()
    {
        dataPayload.clear();
    }
}
