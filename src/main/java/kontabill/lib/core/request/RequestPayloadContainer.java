package main.java.kontabill.lib.core.request;

/**
 * Created by cbitoi on 10/12/15.
 */
public interface RequestPayloadContainer {

    public void addDataItem(String key, Object data);

    public Object getDataItem(String key);

    public Boolean hasDataItem(String key);

    public void removeDataItem(String key);

    public void resetDataItems();

}
