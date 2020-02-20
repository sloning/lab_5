package data;

import movie.Movie;
import movie.MovieGenre;

import java.util.*;

public class MovieCollection {
    private static Map<Long, Movie> Movies = new LinkedHashMap<Long, Movie>();

    public void addMovie(long id, String movieName) {
        Movie movie = new Movie(id, movieName);
        Movies.put(id, movie);
    }

    public void putMovie(long key, Movie movie) {
        Movies.put(key, movie);
    }

    public void removeMovie(Long id) {
        Movies.remove(id);
    }

    public void clearMovies(){
        Movies.clear();
    }

    public Set<Long> getKeySet() {
        return Movies.keySet();
    }

    public Movie getValue(long key) {
        return Movies.get(key);
    }

    public void getInfo() {
        System.out.println("тип коллекции: HashMap");
        System.out.println("количество элементов коллекции: " + Movies.size());
    }

    public void showMovie() {
        System.out.println(Movies.values());
    }

    public void replaceMovie(long key, Movie newMovie) {
        Movies.replace(key, newMovie);
    }

    public Movie getMovie(long key) {
        return Movies.get(key);
    }

    public Map<Long, Movie> getMap() {
        return Movies;
    }
}
