package commands;

import data.MovieCollection;
import movie.Movie;

import java.io.IOException;
import java.util.Scanner;

/**
 * Replace element if it new element has lower length
 *
 * @author Vladislav
 */
public class Replace_if_lowe implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Replace_if_lowe() {
        name = "replace_if_lowe";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": заменить значение по ключу, если новое значение меньше старого";
    }

    /**
     * Get new movie object and compare's it to old movie
     *
     * @param parameter key to HashMap
     */
    @Override
    public String Do(String parameter, Movie movie) throws IOException {
            MovieCollection movieCollection = new MovieCollection();
            Movie oldMovie = movieCollection.getMovie(parameter);

            if (movie.getLength() < oldMovie.getLength()) {
                movieCollection.replaceMovie(parameter, movie);
                return "Значение по ключу успешно заменено";
            }
        return "не удалось заменить значение по ключу";
    }
}
