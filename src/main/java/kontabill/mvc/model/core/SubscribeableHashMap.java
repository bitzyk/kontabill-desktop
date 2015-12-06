package main.java.kontabill.mvc.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */
public class SubscribeableHashMap<K,V> extends HashMap {

    /**
     * Hash map to store the collection
     */
    protected HashMap<Object, Object> observedMap = new HashMap();

    /**
     * List to store hash map listeners
     */
    protected ArrayList<SubscribeableHashMapListener> hashMapListeners = new ArrayList<>();

    protected Boolean threadFinished = false;


    @Override
    public Object put(Object key, Object value) {
        return observedMap.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return observedMap.get(key);
    }

    @Override
    public Set keySet() {
        return observedMap.keySet();
    }

    public void addHashMapListener(SubscribeableHashMapListener listener)
    {
        hashMapListeners.add(listener);
    }

    public void publish()
    {
        Iterator<SubscribeableHashMapListener> listenersIterator = hashMapListeners.iterator();

        while (listenersIterator.hasNext()) {
            SubscribeableHashMapListener listener = listenersIterator.next();
            listener.run(observedMap);
        }
    }

    public Boolean isThreadFinished() {
        return threadFinished;
    }

    public void setThreadFinished(Boolean threadFinished) {
        if(threadFinished == true) {
            publish(); // publish to the listeners outside this thread
        }

        this.threadFinished = threadFinished;
    }

    public HashMap<Object, Object> getObservedMap() {
        return observedMap;
    }
}
