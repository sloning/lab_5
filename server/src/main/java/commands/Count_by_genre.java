package commands;

import Collection.MovieCollection;
import movie.*;
import java.io.IOException;

/**
 * Count how many movies of this genre collection has
 *
 * @author Vladislav
 */
public class Count_by_genre implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Count_by_genre() {
        name = "count_by_genre";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    public String info() {
        return name + ": вывести количество элементов, значения поля genre которых равно заданому";
    }

    /**
     * Iterate through every element of collection to count all movies
     *
     * @param parameter name of genre
     */
    @Override
    public String Do(String parameter, Movie Movie) throws IOException {
            MovieCollection movieCollection = new MovieCollection();

            return "Количество фильмов жанра " + parameter + " - "
                    + movieCollection.getMovies().entrySet().stream()
                    .filter((p) -> p.getValue().getGenre().equals(parameter))
                    .count();

    }
}
