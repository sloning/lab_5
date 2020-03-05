package data;

import movie.Movie;

import java.util.*;

/**
 * This class stores and protects collection with movies
 */
public class MovieCollection {
    /**
     * LinkedHashMap stores every movie
     */
    private static Map<String, Movie> Movies = new LinkedHashMap<>();

    /**
     * Data of creature
     */
    private java.util.Date dateCreation;

    /**
     * Constructor of this class
     */
    public MovieCollection(){
        this.dateCreation = new Date();
    }


    /**
     * Puts movie to collection
     * @param key key to put movie in HashMap
     * @param movie movie object to be put in collection
     */
    public void putMovie(String key, Movie movie) {
        Movies.put(key, movie);
    }

    /**
     * Removes movie by id or key
     * @param key key of movie
     */
    public void removeMovie(String key) {
        Movies.remove(key);
    }

    /**
     * Completely clears collection
     */
    public void clearMovies(){
        Movies.clear();
    }

    /**
     * Returns ids of all movies
     * @return all ids of movies
     */
    public Set<String> getKeySet() {
        return Movies.keySet();
    }

    /**
     * Returns movie by it's id
     * @param key id of movie
     * @return movie object
     */
    public Movie getValue(String key) {
        return Movies.get(key);
    }

    /**
     * Prints info about collection
     */
    public void getInfo() {
        System.out.println("тип коллекции: LinkedHashMap");
        System.out.println("количество элементов коллекции: " + Movies.size());
        System.out.println("дата создания колекции: " + dateCreation);
    }

    /**
     * Iterates through all movies and returns information about them
     * @return info about all movies
     */
    public String showMovies() {
        String moviesInfo = "";
        for (Map.Entry<String, Movie> entry : Movies.entrySet()) {
            Movie movie = entry.getValue();
            moviesInfo = moviesInfo + "\n" + movie.getInfo() + "\nMovie Key: " + entry.getKey();
        }
        return moviesInfo;
    }

    /**
     * Replaces movie by id
     * @param key id of movie to be replaced
     * @param newMovie movie that will replace old movie
     */
    public void replaceMovie(String key, Movie newMovie) {
        Movies.replace(key, newMovie);
    }

    /**
     * Returns movie
     * @param key id of movie
     * @return movie object
     */
    public Movie getMovie(String key) {
        return Movies.get(key);
    }

    /**
     * Returns this collection
     * @return collection with movies
     */
    public Map<String, Movie> getMap() {
        return new LinkedHashMap<>(Movies);
    }
}
