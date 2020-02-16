package movie;
import java.util.LinkedHashMap;
import java.util.Map;

public class MovieCollection {
    private static Map<Integer, Movie> Movies = new LinkedHashMap<Integer, Movie>();

    public static void addProduct(String movieName){
        Movie movie = new Movie(movieName);
        Movies.put(0, movie);
    }
}
