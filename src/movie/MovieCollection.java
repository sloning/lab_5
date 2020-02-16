package movie;
import java.util.*;

public class MovieCollection {
    private static Map<Long, Movie> Movies = new LinkedHashMap<Long, Movie>();
    public static void addMovie(Long id, String movieName){
        Movie movie = new Movie(id, movieName);
        Movies.put(id, movie);
    }
    public static void removeMovie(Long id){
        Movies.remove(id);
    }
    public static void clearMovies(){
        Movies.clear();
    }
}
