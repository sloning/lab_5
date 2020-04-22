package commands;

import Collection.MovieCollection;
import movie.Movie;

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
    public String Do(String parameter1, Movie movie) {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.getMovies().clear();
        return "Коллекция успешно очищена";
    }
}
