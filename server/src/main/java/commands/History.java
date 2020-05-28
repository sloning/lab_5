package commands;
// TODO класс, который ничего не делает
import command_history.CommandHistory;
import movie.Movie;

/**
 * Prints last six commands
 *
 * @author People of the Earth
 */
public class History implements ICommand {
    /**
     * @param name name of command
     */
    private final String name;

    public History() {
        name = "history";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести последние 6 команд (без их аргументов)";
    }

    @Override
    public String Do(String parameter, Movie movie, String user) {
        CommandHistory commandHistory = new CommandHistory();
        String result = commandHistory.printHistory();
        return result;
    }
}
