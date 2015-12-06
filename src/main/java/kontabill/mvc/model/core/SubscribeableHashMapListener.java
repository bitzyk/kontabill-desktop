package main.java.kontabill.mvc.model.core;

import java.util.HashMap;

/**
 * Created by cbitoi on 24/11/15.
 */
public interface SubscribeableHashMapListener {

    public void run(HashMap<Object, Object> observedMap);
}
