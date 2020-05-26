package commands;

import Collection.MovieCollection;
import DB.DBWorker;
import movie.Movie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Replace movie by it's id
 *
 * @author Abay
 */
public class Update implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Update() {
        name = "update";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": обновить значение элемента коллекции, ключ которого равен зададному";
    }

    /**
     * Replaces old movie by new movie
     *
     * @param parameter key to HashMap and id of new movie
     */
    @Override //Гениальный класс
    public String Do(String parameter, Movie movie, String user) throws IOException {
            MovieCollection movieCollection = new MovieCollection();
            movieCollection.getMovies().replace(parameter, movie);

        try {
            Statement statement = DBWorker.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate("delete from movies where movie_key = '" + parameter + "'");
            String CoordinatesQuery = "insert into coords(x,y) values (" + movie.getCoordinatesX() + ", " + movie.getCoordinatesY() + ")";
            statement.executeUpdate(CoordinatesQuery);
            ResultSet resultCoordinatesSet = statement.executeQuery("select coords_id from coords");
            resultCoordinatesSet.afterLast(); //переводит курсор на последний добавленный элемент
            resultCoordinatesSet.previous();
            String CoordinatesId = resultCoordinatesSet.getString(1);
            String LocationQuery = "insert into locations(x,y,z,location_name) values (" + movie.getDirectorLocationX() + ", " + movie.getDirectorLocationY() + ", " + movie.getDirectorLocationZ() + ", '" + movie.getDirectorLocationName() + "')";
            statement.executeUpdate(LocationQuery);
            ResultSet resultLocationSet = statement.executeQuery("select location_id from locations");
            resultLocationSet.afterLast();
            resultLocationSet.previous();
            String LocationId = resultLocationSet.getString(1);
            String DirectorQuery = "insert into directors(director_name, birthday, height, weight, location) values ('" + movie.getDirectorName() + "', '" + movie.getDirectorBirthday() + "', " + movie.getDirectorHeight() + ", " + movie.getDirectorWeight() + ", '" + LocationId + "')";
            statement.executeUpdate(DirectorQuery);
            ResultSet resultDirectorSet = statement.executeQuery("select director_id from directors");
            resultDirectorSet.afterLast();
            resultDirectorSet.previous();
            String DirectorId = resultDirectorSet.getString(1);
            String MovieQuery = "insert into movies(movie_name, movie_coords, date_of_creation, oscars, length, movie_genre, movie_rating, director, movie_key) values ('"
                    + movie.getName() + "', '" + CoordinatesId + "', '" + movie.getCreationDate() + "', " + movie.getOscars() + ", " + movie.getLength() + ", '" + movie.getGenre().toString() + "', '"
                    + movie.getMpaaRating().toString() + "', '" + DirectorId + "', '" + parameter + "')";
            statement. executeUpdate(MovieQuery);

            resultCoordinatesSet.close();
            resultLocationSet.close();
            resultDirectorSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "Объект по ключу " + parameter + " успешно обновлен";
    }
}