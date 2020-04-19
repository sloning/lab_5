package client.src.main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores commands
 */
public class CommandHistory {
    /**
     * Place there commands stores
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
     * Prints last six commands
     */
    public void printHistory() {
        System.out.println(history.subList(Math.max(history.size() - 6, 0), history.size()));
    }
}
