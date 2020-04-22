package Collection;

import movie.*;

import java.util.*;
import java.util.function.Predicate;

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
    public MovieCollection() {
        this.dateCreation = new Date();
    }

    public Map<String, Movie> getMovies() {
        return Movies;
    }

    /**
     * Puts movie to collection
     *
     * @param key   key to put movie in HashMap
     * @param movie movie object to be put in collection
     */
    public void putMovie(String key, Movie movie) {
        Movies.put(key, movie);
    }

    /**
     * Returns ids of all movies
     *
     * @return all ids of movies
     */
    public Set<String> getKeySet() {
        return Movies.keySet();
    }

    /**
     * Returns movie by it's id
     *
     * @param key id of movie
     * @return movie object
     */
    public Movie getValue(String key) {
        return Movies.get(key);
    }


    /**
     * Replaces movie by id
     *
     * @param key      id of movie to be replaced
     * @param newMovie movie that will replace old movie
     */
    public void replaceMovie(String key, Movie newMovie) {
        Movies.replace(key, newMovie);
    }

    /**
     * Returns movie
     *
     * @param key id of movie
     * @return movie object
     */
    public Movie getMovie(String key) {
        return Movies.get(key);
    }

    public void setMovies(Map<String, Movie> newMovies){
        Movies = newMovies;
    }

    /**
     * Returns this collection
     *
     * @return collection with movies
     */
    public Map<String, Movie> getMap() {
        return new LinkedHashMap<>(Movies);
    }

    public Date getDateCreation() {
        return dateCreation;
    }

}