package commands;

import Collection.MovieCollection;
import DB.DBWorker;
import movie.Movie;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Insert movie by key
 *
 * @author Abay
 */
public class Insert_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

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
    public String Do(String parameter, Movie movie) throws IOException {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.getMovies().put(parameter, movie);
        try {
            Statement statement = DBWorker.getConnection().createStatement();
            String CoordinatesQuery = "insert into coords(x,y) values (" + movie.getCoordinatesX() + ", " + movie.getCoordinatesY() + ")";
            statement.addBatch(CoordinatesQuery);
            String LocationQuery = "insert into locations(location_name,x,y,z) values (" + movie.getDirectorLocationName() + ", " + movie.getDirectorLocationX() + ", " + movie.getDirectorLocationY() + ", " + movie.getDirectorLocationZ() + ")";
            statement.addBatch(LocationQuery);
            //String DirectorQuery = "insert into directors(director_name, birthday, height, weight)"
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "В коллекцию успешно добавлен фильм " + movie.getName();
    }
}
