package main.java.kontabill.mvc.view;

import main.java.kontabill.mvc.view.view_commands.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Records instances of view commands executed by the app
 */
public abstract class ComponentRegistry {

    public final static Map<String, Object> COMPONENT_COLLECTION = new HashMap<>();

    /**
     * @param key
     * @return
     */
    public static Object getComponent(String key)
    {
        Object component = COMPONENT_COLLECTION.get(key);

        return component;
    }

    public static void addComponent(String key, Object component)
    {
        COMPONENT_COLLECTION.put(key, component);
    }

}
