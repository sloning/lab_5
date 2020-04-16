package client.commands;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Command module
 * Controls all client.commands
 *
 * @author Abay
 */
public class Commands {
    /**
     * Collection with object of all client.commands
     */
    private static Map<String, ICommand> commands = new TreeMap<>();

    /**
     * Searches command through TreeMap and calls method Do
     *
     * @param name       name of command
     * @param parameter1 first parameter to transmit to command
     */
    public Commands(String name, String parameter1) throws IOException {
        commands.get(name).Do(parameter1);
    }

    /**
     * Adds new command to collection
     *
     * @param name    name of command
     * @param command object of command
     */
    public static void addNewCommand(String name, ICommand command) {
        commands.put(name, command);
    }

    static void help() {
        Collection<ICommand> Val = commands.values();
        for (ICommand com : Val) {
            System.out.println(com.info());
        }
    }
}
