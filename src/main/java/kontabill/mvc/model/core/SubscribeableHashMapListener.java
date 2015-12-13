package main.java.kontabill.mvc.model.core;

import java.util.HashMap;

/**
 * Created by cbitoi on 24/11/15.
 */
public interface SubscribeableHashMapListener<K, V> {

    public void run(HashMap<K, V> observedMap);
}
