package movie;
import java.util.*;

public class MovieCollection {
    private static Map<Long, Movie> Movies = new LinkedHashMap<Long, Movie>();

    public static void addMovie(Long id, String movieName) {
        Movie movie = new Movie(id, movieName);
        Movies.put(id, movie);
    }


    public static void removeMovie(Long id){
        Movies.remove(id);
    }

    public static void clearMovies(){
        Movies.clear();
    }

    public static Set<Long> getKeySet() {
        return Movies.keySet();
    }

    public static Movie getValue(Long key) {
        return Movies.get(key);
    }

    public static void getInfo(){
        System.out.println("тип коллекции: HashMap");
        System.out.println("количество элементов коллекции: " + Movies.size());
    }

    public static void showMovie(){
        Collection<Movie> values = Movies.values();
        System.out.println(values);
    }

    public static void replaceMovie(Long key, String movieName){
        Movie movie = new Movie(key, movieName);
        Movies.replace(key, movie);
    }
}
