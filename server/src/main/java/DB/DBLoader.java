package DB;

import Collection.MovieCollection;
import movie.Movie;

import java.sql.*;

public class DBLoader {
    public DBLoader(){
        try {
            Statement statement = DBWorker.getConnection().createStatement();
            MovieCollection movieCollection = new MovieCollection();
            ResultSet resultMoviesSet = statement.executeQuery("select * from movies");
            while (resultMoviesSet.next()) {
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
                while (resultCoordsSet.next()) {
                    movie.setCoordinates(resultCoordsSet.getInt("x"), resultCoordsSet.getFloat("y"));
                }
                ResultSet resultDirectorSet = statement.executeQuery("select * from directors where director_id = '" + directorId + "'");
                while (resultDirectorSet.next()) {
                    String locationId = resultDirectorSet.getString("location");
                    String directorName = resultDirectorSet.getString("director_name");
                    Double directorHeight = resultDirectorSet.getDouble("height");
                    Float directorWeight = resultDirectorSet.getFloat("weight");
                    ResultSet resultLocationSet = statement.executeQuery("select * from locations where location_id = '" + locationId + "'");
                    while (resultLocationSet.next()) {
                        String locationName = resultLocationSet.getString("location_name");
                        int locationX = resultLocationSet.getInt("x");
                        long locationY = resultLocationSet.getLong("y");
                        int locationZ = resultLocationSet.getInt("z");
                        movie.setDirectorWithLocation(directorName, directorHeight, directorWeight, locationName, locationX, locationY, locationZ);
                    }
                    resultLocationSet.close();
                }
                resultDirectorSet.close();
                resultCoordsSet.close();
                movieCollection.putMovie(key, movie);
            }
            resultMoviesSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
