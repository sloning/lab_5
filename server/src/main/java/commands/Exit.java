package commands;

import movie.Movie;

/**
 * Stops program execution
 *
 * @author Vladislav
 */
public class Exit implements ICommand {
    /**
     * @param name name of command
     */
    private final String name;

    public Exit() {
        name = "exit";
        Commands.addNewCommand("exit", this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": завершить программу (без сохранения)";
    }

    @Override
    public String Do(String parameter, Movie movie, String user) {
        System.exit(0);
        return "Выход из программы";
    }
}
