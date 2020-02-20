package data;

import java.util.ArrayDeque;

/**
 * Stores last 6 commands
 */
public class CommandHistory {
    /**
     * Place there commands stores
     */
    private static ArrayDeque<String> history = new ArrayDeque<String>(6);

    /**
     * Adds new command to collection and removes the oldest
     * @param commandName Name of command to be adds
     */
    public void addCommand(String commandName) {
        history.add(commandName);
    }

    /**
     * Prints last six commands
     */
    public void printHistory() {
        System.out.println(history);
    }
}
