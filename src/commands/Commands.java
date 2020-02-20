package commands;

import java.io.IOException;
import java.util.*;

/**
 * Command module
 * Controls all commands
 * @author Abay
 */
public class Commands {
    /**
     * Collection with object of all commands
     */
    private static Map<String, ICommand> commands = new TreeMap<>();

    /**
     * Adds new command to collection
     * @param name name of command
     * @param command object of command
     */
    public static void addNewCommand(String name, ICommand command){
        commands.put(name, command);
    }

    /**
     * Searches command through TreeMap and calls method Do
     * @param name name of command
     * @param parameter1 first parameter to transmit to command
     * @param parameter2 second parameter to transmit to command
     */
    public Commands(String name, String parameter1, String parameter2) throws IOException {
       commands.get(name).Do(parameter1, parameter2);
    }

}
