package commands;

import Collection.MovieCollection;
import DB.DBWorker;
import movie.Movie;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

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

        try {
            Statement statement = DBWorker.getConnection().createStatement();
            statement.executeUpdate("delete from movies where movie_key = '" + parameter + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "Значение по ключу " + parameter + " успешно удалено";
    }
}
