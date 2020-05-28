package Collection;

import DB.DBWorker;
import commands.Commands;
import movie.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class SaveCollection {
    public void save() {
        try {
            Statement statement = DBWorker.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            MovieCollection movieCollection = new MovieCollection();
            statement.executeUpdate("delete from movies");
            statement.executeUpdate("delete from directors");
            statement.executeUpdate("delete from coords");
            statement.executeUpdate("delete from locations");

            for (Map.Entry<String, Movie> elementOfMap : movieCollection.getMap().entrySet()) {
                Movie movie = elementOfMap.getValue();
                String key = elementOfMap.getKey();
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
                String rating = (movie.getMpaaRating() != null) ? movie.getMpaaRating().toString() : null;
                String MovieQuery = "insert into movies(movie_name, movie_coords, date_of_creation, oscars, length, movie_genre, movie_rating, director, movie_key, usernames) values ('"
                        + movie.getName() + "', '" + CoordinatesId + "', '" + movie.getCreationDate() + "', " + movie.getOscars() + ", " + movie.getLength() + ", '" + movie.getGenre() + "', '"
                        + rating + "', '" + DirectorId + "', '" + key + "', '" + movie.getUser() + "')";
                statement.executeUpdate(MovieQuery);
                resultCoordinatesSet.close();
                resultLocationSet.close();
                resultDirectorSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void checkForCommand() {
        Thread backgroundReaderThread = new Thread(() -> {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!Thread.interrupted()) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (line.equalsIgnoreCase("save")) {
                        System.out.println("SAVING...");
                        save();
                        System.out.println("SAVED.");
                    }
                    if (line.equalsIgnoreCase("exit")) {
                        Commands useCommands = new Commands("exit", null, null, null);
                        useCommands.execute();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}
