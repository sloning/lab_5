package command_history;

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
     * @return
     */
    public String printHistory() {
        String result = null;
        List<String> print = history.subList(Math.max(history.size() - 6, 0), history.size());
        for (int i=0; i<print.size(); i++){
            result = print.get(i);
        }
        return result;
    }
}
