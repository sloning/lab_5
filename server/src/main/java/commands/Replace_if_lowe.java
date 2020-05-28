package commands;

import Collection.MovieCollection;
import movie.Movie;

/**
 * Replace element if it new element has lower length
 *
 * @author Vladislav
 */
public class Replace_if_lowe implements ICommand {
    /**
     * @param name name of command
     */
    private final String name;

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
    public String Do(String parameter, Movie movie, String user) {
        MovieCollection movieCollection = new MovieCollection();
        if (movieCollection.getMovies().entrySet().stream()
                .filter(p -> p.getKey().equals(parameter))
                .filter(p -> p.getValue().getLength() > movie.getLength())
                .allMatch(p -> p.getValue().getUser().equals(user))) {
            if (movieCollection.getMovies().entrySet().stream()
                    .filter(p -> p.getKey().equals(parameter))
                    .allMatch(p -> p.getValue().getLength() > movie.getLength())) {
                movieCollection.getMovies().replace(parameter, movie);
                return "Значение по ключу успешно заменено";
            }
        } else {
            return "Вам не принадлежит ни 1 фильм, подходящий под условие";
        }
        return "Не удалось заменить значение по ключу";
    }
}
