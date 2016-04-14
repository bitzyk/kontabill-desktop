package main.java.kontabill.mvc.view.view_commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Records instances of view commands executed by the app
 */
public abstract class CommandRegistry {

    public final static Map<String, Command> COMMAND_MAP = new HashMap<>();


    /**
     * Get the last command for the key given
     *
     * @param key
     * @return
     */
    public static Command getCommand(String key)
    {
        Command command = COMMAND_MAP.get(key);

        return command;
    }

    public static void addCommand(String key, Command command)
    {
        COMMAND_MAP.put(key, command); // overwrite if exist command with the same key
    }

}
