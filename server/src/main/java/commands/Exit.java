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
    private String name;

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
        return name + ": завершить программу (без сохранения в файл)";
    }

    @Override
    public String Do(String parameter, Movie movie, String user) {
        System.exit(0);
        return "выход из программы";
    }
}
