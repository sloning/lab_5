package commands;

import Collection.MovieCollection;
import movie.Movie;

import java.io.IOException;
import java.util.Map;

/**
 * Insert movie by key
 *
 * @author Abay
 */
public class Insert_key implements ICommand {
    /**
     * @param name name of command
     */
    private final String name;

    public Insert_key() {
        name = "insert";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": добавить новый элемент с заданным ключом";
    }


    /**
     * Replaces movie by id
     *
     * @param parameter key to HashMap to insert movie
     */
    @Override
    public String Do(String parameter, Movie movie, String user) throws IOException {
        MovieCollection movieCollection = new MovieCollection();
        boolean exists = false;
        for (Map.Entry<String, Movie> elementOfMap : movieCollection.getMap().entrySet()) {
            String movieKey = elementOfMap.getKey();
            if (movieKey.equals(parameter)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            movie.setUser(user);
            movieCollection.getMovies().put(parameter, movie);
            return "В базу данных успешно добавлен фильм " + movie.getName();
        } else {
            return "Не удалось добавить фильм. Фильм по заданному ключу " + parameter + " уже существует";
        }
    }
}
