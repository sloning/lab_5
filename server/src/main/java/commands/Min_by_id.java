package commands;

import Collection.MovieCollection;
import movie.Movie;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Printing element with minimal id
 *
 * @author Vladislav
 */
public class Min_by_id implements ICommand {
    /**
     * @param name name of command
     */
    private final String name;

    public Min_by_id() {
        name = "min_by_id";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести любой объект из коллекции, значение поля id которого является минимальным";
    }

    /**
     * Get Key Set from HashMap then take lowest key (key = id) and print it's value
     *
     * @param parameter ignore this
     */
    @Override
    public String Do(String parameter, Movie movie, String user) {
        MovieCollection movieCollection = new MovieCollection();
        String result = "В коллекции нет элементов";
        try {
            result = movieCollection.getMovies().entrySet().stream()
                    .min(Comparator.comparing(p -> p.getValue().getId()))
                    .get().toString();
        } catch (NoSuchElementException ignored) {
        }
        return result;
    }
}
