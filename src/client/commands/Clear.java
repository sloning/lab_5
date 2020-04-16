package client.commands;

import server.data.MovieCollection;

/**
 * Removes all movies from collection
 *
 * @author Abay
 */
public class Clear implements ICommand {
    /**
     * @param name = name of command
     */
    private String name;
    Commands commands;
    public Clear() {
        this.name = "clear";
        Commands.addNewCommand(name, this);

    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": очистить коллекцию";
    }

    /**
     * @param parameter1 ignore this
     */
    @Override
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.clearMovies();
    }

}
