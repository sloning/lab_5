package data;
import movie.Movie;

import java.util.*;

public class MovieCollection { //TODO закинуть в отдельный пэкидж с данными
    private static Map<Long, Movie> Movies = new LinkedHashMap<Long, Movie>();

    public void addMovie(long id, String movieName) {
        Movie movie = new Movie(id, movieName);
        Movies.put(id, movie);
    }

    public void putMovie(long key, Movie movie){
        Movies.put(key,movie);
    }

    public void removeMovie(Long id){
        Movies.remove(id);
    }

    public void clearMovies(){
        Movies.clear();
    }

    public Set<Long> getKeySet() {
        return Movies.keySet();
    }

    public Movie getValue(int key) {
        return Movies.get(key);
    }

    public void getInfo(){
        System.out.println("тип коллекции: HashMap");
        System.out.println("количество элементов коллекции: " + Movies.size());
    }

    public void showMovie(){
        Collection<Movie> values = Movies.values();
        System.out.println(values);
    }

    public void replaceMovie(long key, String movieName){
        Movie movie = new Movie(key, movieName);
        Movies.replace(key, movie);
    }
}