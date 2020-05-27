package Collection;

import DB.DBWorker;
import movie.Movie;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

import static data.FileCheck.checkFile;

public class SaveCollection {
    private Thread backgroundReaderThread = null;

    public void save() throws IOException {     //TODO переписать под БД
        try {
            Statement statement = DBWorker.getConnection().createStatement();
            MovieCollection movieCollection = new MovieCollection();
            statement.executeUpdate("delete from movies");
            statement.executeUpdate("delete from directors");
            statement.executeUpdate("delete from coords");
            statement.executeUpdate("delete from locations");
            Collection<String> collection= movieCollection.getMovies().keySet();
            for(Movie movie : movieCollection.getMovies().values()) {
                String key = null;
                for (String key1 : collection) {
                    Movie obj = movieCollection.getMovies().get(key1);
                    if (key1 != null) {
                        if (movie.equals(obj)) {
                            key = key1;//
                        }
                    }
                }
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
                String MovieQuery = "insert into movies(movie_name, movie_coords, date_of_creation, oscars, length, movie_genre, movie_rating, director, movie_key, usernames) values ('"
                        + movie.getName() + "', '" + CoordinatesId + "', '" + movie.getCreationDate() + "', " + movie.getOscars() + ", " + movie.getLength() + ", '" + movie.getGenre().toString() + "', '"
                        + movie.getMpaaRating().toString() + "', '" + DirectorId + "', '" + key + "', '" + movie.getUser() + "')";
                statement.executeUpdate(MovieQuery);

                resultCoordinatesSet.close();
                resultLocationSet.close();
                resultDirectorSet.close();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void checkForSaveCommand() throws IOException {
        backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("save")) save();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}
