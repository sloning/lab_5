package commands;

import Collection.MovieCollection;
import movie.Movie;

import java.util.Map;

/**
 * Removes all movies from collection
 *
 * @author Abay
 */
public class Clear implements ICommand {
    /**
     * @param name = name of command
     */
    private final String name;

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
    public String Do(String parameter1, Movie nemovie, String user) {
        MovieCollection movieCollection = new MovieCollection();
        for (Map.Entry<String, Movie> elementOfMap : movieCollection.getMap().entrySet()) {
            Movie movie = elementOfMap.getValue();
            String movieKey = elementOfMap.getKey();
            int i = 0;
            if (user.equals(movie.getUser())) {
                i++;
                movieCollection.getMovies().remove(movieKey);
            }
            if (i > 0) {
                return "Все фильмы, принадлежащие вам успешно удалены из коллекции (в количестве - " + i + ")";
            }
        }
        if (movieCollection.getSize() > 0) {
            return "В коллекции нет фильмов, которые вам принадлежат";
        } else {
            return "Коллекция итак пуста";
        }
    }
}
