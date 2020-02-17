package data;
import data.Collections;
import movie.Movie;

import java.util.*;

public class MovieCollection { //TODO закинуть в отдельный пэкидж с данными
    private static Map<Integer, Movie> Movies = new LinkedHashMap<Integer, Movie>();

    public void addMovie(int length, String movieName) {
        Movie movie = new Movie(length, movieName);
        Movies.put(length, movie);
    }


    public void removeMovie(int length){
        Movies.remove(length);
    }

    public void clearMovies(){
        Movies.clear();
    }

    public Set<Integer> getKeySet() {
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

    public void replaceMovie(int key, String movieName){
        Movie movie = new Movie(key, movieName);
        Movies.replace(key, movie);
    }
}
