package main.java.kontabill.mvc.model.core;

import java.util.*;

/**
 *
 */
public class SubscribeableHashMap<K,V> extends HashMap {

    /**
     * Hash map to store the collection
     */
    protected HashMap<K, V> observedMap = new LinkedHashMap<>();

    /**
     * List to store hash map listeners
     */
    protected ArrayList<SubscribeableHashMapListener> hashMapListeners = new ArrayList<>();

    protected Boolean threadFinished = false;


    public V putInMap(K key, V value) {
        return observedMap.put(key, value);
    }

    @Override
    public void putAll(Map m) {
        observedMap.putAll(m);
    }

//public V setMap()

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

    public HashMap<K, V> getObservedMap() {
        return observedMap;
    }

    @Override
    public int size() {
        return observedMap.size();
    }
}
