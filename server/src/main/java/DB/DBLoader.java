package DB;

import Collection.MovieCollection;
import movie.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBLoader {
    public DBLoader(){
        try {
            Statement statement = DBWorker.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            MovieCollection movieCollection = new MovieCollection();
            List<Movie> movies = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select * from movies");

            int i = -1;
            while (resultSet.last()) {
                i = resultSet.getRow();
            }
            ResultSet resultMoviesSet = statement.executeQuery("select * from movies");
            while (i > 0) {
                Movie movie = new Movie();
                String key = resultMoviesSet.getString("movie_key");
                movie.setName(resultMoviesSet.getString("movie_name"));
                movie.setLength(resultMoviesSet.getInt("length"));
                movie.setOscarsCount(resultMoviesSet.getInt("oscars"));
                movie.setGenre(resultMoviesSet.getString("movie_genre"));
                movie.setMpaaRating(resultMoviesSet.getString("movie_rating"));
                movie.setCreationDate(resultMoviesSet.getDate("date_of_creation"));
                String directorId = resultMoviesSet.getString("director");
                ResultSet resultCoordsSet = statement.executeQuery("select * from coords where coords_id = '" + resultMoviesSet.getString("movie_coords") + "'");
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

                while (resultDirectorSet.next()) {
                    locationId = resultDirectorSet.getString("location");
                    directorName = resultDirectorSet.getString("director_name");
                    directorHeight = resultDirectorSet.getDouble("height");
                    directorWeight = resultDirectorSet.getFloat("weight");
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
                movie.setDirectorWithLocation(directorName, directorHeight, directorWeight, locationName, locationX, locationY, locationZ);

                movieCollection.putMovie(key, movie);
                i--;
                resultMoviesSet.next();
            }
        resultMoviesSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
