package commands;

import Collection.MovieCollection;
import DB.DBWorker;
import movie.Movie;

import java.sql.SQLException;
import java.sql.Statement;

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
    public String Do(String parameter1, Movie movie) {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.getMovies().clear();

        try {
            Statement statement = DBWorker.getConnection().createStatement();
            statement.executeUpdate("delete from movies");
            statement.executeUpdate("delete from directors");
            statement.executeUpdate("delete from locations");
            statement.executeUpdate("delete from coords");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Коллекция успешно очищена";
    }
}
