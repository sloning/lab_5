package commands;

import Collection.MovieCollection;
import movie.Movie;

import java.io.IOException;

/**
 * Removes movie by key
 *
 * @author Abay
 */
public class Remove_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Remove_key() {
        name = "remove";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": удалить элемент из коллекции по его ключу";
    }


    /**
     * Removes movie by key
     *
     * @param parameter key of movie to remove
     */
    @Override
    public String Do(String parameter, Movie movie) throws IOException {
            MovieCollection movieCollection = new MovieCollection();
            movieCollection.getMovies().remove(parameter);

        return "Значение по ключу " + parameter + " успешно удалено";
    }
}
