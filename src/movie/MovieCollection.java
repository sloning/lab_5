package movie;
import java.util.LinkedHashMap;
import java.util.Map;

public class MovieCollection {
    private static Map<Long, Movie> Movies = new LinkedHashMap<Long, Movie>();
    private static long id = 0;

    public static void addProduct(String movieName){
        Movie movie = new Movie(movieName, id);
        Movies.put(id++, movie);
    }
}
