package commands;

import Collection.MovieCollection;
import DB.DBWorker;
import movie.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        try {
            Statement statement = DBWorker.getConnection().createStatement();
            MovieCollection movieCollection = new MovieCollection();
            ResultSet resultSet = statement.executeQuery("select * from movies where usernames = '" + user + "'");
            boolean f = false;
            while (resultSet.next()) {
                f = true;
            }

            if (f) {
                ResultSet resultDirectorsSet = statement.executeQuery("select director_id from directors where director_id in (select director from movies where usernames = '" + user + "')");
                List<String> DirectorsArray = new ArrayList<String>();
                while (resultDirectorsSet.next()) {
                    DirectorsArray.add(resultDirectorsSet.getString(1));
                }
                ResultSet resultCoordsSet = statement.executeQuery("select coords_id from coords where coords_id in (select movie_coords from movies where usernames = '" + user + "')");
                List<String> CoordsArray = new ArrayList<String>();
                while (resultCoordsSet.next()) {
                    CoordsArray.add(resultCoordsSet.getString(1));
                }
                List<String> LocationArray = new ArrayList<String>();
                for (int i = 0; i < DirectorsArray.size(); i++) {
                    ResultSet resultLocationSet = statement.executeQuery("select location_id from locations where location_id in (select location from directors where director_id = '" + DirectorsArray.get(i) + "')");
                    LocationArray.add(resultLocationSet.getString(1));
                }
                ResultSet resultMoviesSet = statement.executeQuery("select movie_key from movies where usernames = '" + user + "'");
                List<String> MoviesKeyArray = new ArrayList<>();
                while (resultMoviesSet.next()) {
                    MoviesKeyArray.add(resultMoviesSet.getString(1));
                }

                statement.executeUpdate("delete from movies where usernames = '" + user + "'");

                for (int i = 0; i < DirectorsArray.size(); i++) {
                    statement.executeUpdate("delete from directors where director_id = '" + DirectorsArray.get(i) + "'");
                }

                for (int i = 0; i < LocationArray.size(); i++) {
                    statement.executeUpdate("delete from locations where location_id = '" + LocationArray.get(i) + "'");
                }

                for (int i = 0; i < CoordsArray.size(); i++) {
                    statement.executeUpdate("delete from coords where coords_id = '" + CoordsArray.get(i) + "'");
                }

                for (int i = 0; i < MoviesKeyArray.size(); i++) {
                    movieCollection.getMovies().remove(MoviesKeyArray.get(i));
                }

                return "Все фильмы, принадлежащие вам, удачно удалены";
            } else {
                if (movieCollection.getSize() == 0) {
                    return "Коллекция уже пуста";
                } else {
                    return "Вам не принадлежит ни один фильм в коллекции";
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Не удалось очистить коллекцию";
        }
    }
}
