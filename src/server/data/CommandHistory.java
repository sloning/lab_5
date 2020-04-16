package server.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores client.commands
 */
public class CommandHistory {
    /**
     * Place where client.commands stores
     */
    private static List<String> history = new ArrayList<String>();

    /**
     * Adds new command to collection and removes the oldest
     *
     * @param commandName Name of command to be adds
     */
    public void addCommand(String commandName) {
        history.add(commandName);
    }

    /**
     * Prints last six client.commands
     */
    public void printHistory() {
        System.out.println(history.subList(Math.max(history.size() - 6, 0), history.size()));
    }
}
