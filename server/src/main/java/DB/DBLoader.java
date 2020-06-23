package DB;

import Collection.MovieCollection;
import movie.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

public class DBLoader {
    public DBLoader(){
        try {
            Statement statement = DBWorker.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            MovieCollection movieCollection = new MovieCollection();
            ResultSet resultSet = statement.executeQuery("select * from movies");

            int i = -1;
            resultSet.last();
            i = resultSet.getRow();

            for (int c = 1; c <= i; c++) {
                ResultSet resultMoviesSet = statement.executeQuery("select * from movies");
                resultMoviesSet.absolute(c);
                resultMoviesSet.previous();
                resultMoviesSet.next();
                Movie movie = new Movie();
                String key = resultMoviesSet.getString("movie_key");
                movie.setName(resultMoviesSet.getString("movie_name"));
                movie.setLength(resultMoviesSet.getInt("length"));
                movie.setOscarsCount(resultMoviesSet.getInt("oscars"));
                movie.setGenre(resultMoviesSet.getString("movie_genre"));
                movie.setMpaaRating(resultMoviesSet.getString("movie_rating"));
                Timestamp timestamp = resultMoviesSet.getTimestamp("date_of_creation");
                Date date = new Date();
                if (timestamp != null)
                    date = new java.util.Date(timestamp.getTime());
                movie.setCreationDate(date);
                String directorId = resultMoviesSet.getString("director");
                String coordsId = resultMoviesSet.getString("movie_coords");
                String user = resultMoviesSet.getString("usernames");
                movie.setUser(user);
                resultMoviesSet.close();

                ResultSet resultCoordsSet = statement.executeQuery("select * from coords where coords_id = '" + coordsId + "'");
                int x = 0;
                float y = 0;
                while (resultCoordsSet.next()) {
                    x = resultCoordsSet.getInt("x");
                    y = resultCoordsSet.getFloat("y");
                }
                movie.setCoordinates(x, y);
                ResultSet resultDirectorSet = statement.executeQuery("select * from directors where director_id = '" + directorId + "'");
                String locationId = null;
                String directorName = null;
                double directorHeight = 0;
                float directorWeight = 0;
                Date birthDate = new Date();

                while (resultDirectorSet.next()) {
                    locationId = resultDirectorSet.getString("location");
                    directorName = resultDirectorSet.getString("director_name");
                    directorHeight = resultDirectorSet.getDouble("height");
                    directorWeight = resultDirectorSet.getFloat("weight");
                    birthDate = resultDirectorSet.getDate("birthday");
                }

                ResultSet resultLocationSet = statement.executeQuery("select * from locations where location_id = '" + locationId + "'");

                String locationName = null;
                int locationX = 0;
                long locationY = 0;
                int locationZ = 0;

                while (resultLocationSet.next()) {
                    locationName = resultLocationSet.getString("location_name");
                    locationX = resultLocationSet.getInt("x");
                    locationY = resultLocationSet.getLong("y");
                    locationZ = resultLocationSet.getInt("z");
                }
                movie.setDirectorWithLocationAndDate(directorName, directorHeight, directorWeight, locationName, locationX, locationY, locationZ, birthDate);

                movieCollection.putMovie(key, movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
