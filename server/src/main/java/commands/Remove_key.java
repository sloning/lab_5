package commands;

import Collection.MovieCollection;
import DB.DBWorker;
import movie.Movie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public String Do(String parameter, Movie movie, String user) throws IOException {
            MovieCollection movieCollection = new MovieCollection();

        try {
            Statement statement = DBWorker.getConnection().createStatement();
            ResultSet resultMoviesSet = statement.executeQuery("select movie_key from movies where movie_key = '" + parameter + "' and usernames = '" + user + "'");
            int i = 0;
            while (resultMoviesSet.next()){
                i++;
            }
            if (i == 0) {
                if (movieCollection.getMovies().get(parameter) == null) {
                    return "Значения по ключу " + parameter + " не существует";
                } else
                return "Нельзя удалить фильм по ключу " + parameter + ", потому что он вам не принадлежит";
            } else {
                statement.executeUpdate("delete from movies where movie_key = '" + parameter + "' and usernames = '" + user + "'");
                movieCollection.getMovies().remove(parameter);
                return "Значение по ключу " + parameter + " успешно удалено";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Произошла ошибка при удалении фильма";
        }
    }
}
