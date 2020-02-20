package data;

import java.util.ArrayDeque;

public class CommandHistory {
    private static ArrayDeque<String> history = new ArrayDeque<String>(6);
    public void addCommand(String commandName) {
        history.add(commandName);
    }

    public void printHistory() {
        System.out.println(history);
    }
}
