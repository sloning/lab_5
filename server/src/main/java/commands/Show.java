package commands;

import data.MovieCollection;
import movie.Movie;

/**
 * Prints all movies in collection
 *
 * @author Abay
 */
public class Show implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Show() {
        name = "show";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String Do(String parameter, Movie movie) {
        MovieCollection movieCollection = new MovieCollection();
        return movieCollection.showMovies();
    }
}
