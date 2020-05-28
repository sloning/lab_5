package commands;

import Collection.MovieCollection;
import DB.DBWorker;
import movie.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
    public String Do(String parameter1, Movie movie, String user) {
        MovieCollection movieCollection = new MovieCollection();
        for(Movie movieC : movieCollection.getMovies().values()) {
            int i = 0;
            if (user.equals(movieC.getUser()))  {
                i++;
                Collection<String> collection= movieCollection.getMovies().keySet();
                String key = null;
                for (String key1 : collection) {
                    Movie obj = movieCollection.getMovies().get(key1);
                    if (key1 != null) {
                        if (movie.equals(obj)) {
                            key = key1;//
                        }
                    }
                }
                movieCollection.getMovies().remove(key);
            }
            if (i>0) {
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
