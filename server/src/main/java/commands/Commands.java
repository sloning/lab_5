package commands;

import command_history.CommandHistory;
import movie.Movie;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Command module
 * Controls all commands
 *
 * @author Abay
 */
public class Commands {
    /**
     * Collection with object of all commands
     */
    private static final Map<String, ICommand> commands = new TreeMap<>();
    private final String name;
    private final String parameter;
    private final Movie movie;

    /**
     * Searches command through TreeMap and calls method Do
     *
     * @param name      name of command
     * @param parameter first parameter to transmit to command
     */
    public Commands(String name, String parameter, Movie movie) {
        this.name = name;
        this.parameter = parameter;
        this.movie = movie;
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand(name);
    }

    public String execute() throws IOException {
        return commands.get(name).Do(parameter, movie);
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

    static String help() {
        Collection<ICommand> Val = commands.values();
        String result = "";
        for (ICommand com : Val) {
            result += com.info() + "\n";
        }
        return result;
    }
}
