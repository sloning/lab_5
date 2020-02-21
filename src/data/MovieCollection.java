package data;

import input_output.InputMovie;
import movie.Location;
import movie.Movie;

import java.util.*;

/**
 * This class stores and protects collection with movies
 */
public class MovieCollection {
    /**
     * LinkedHashMap stores every movie
     */
    private static Map<Long, Movie> Movies = new LinkedHashMap<Long, Movie>();

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
     * Adds new movie to collection
     * @param movieName name of new film
     */
    public void addMovie(String movieName) {
        InputMovie inputMovie = new InputMovie();
        /**
         * almost uniq id of new movie
         */
        long id = UUID.randomUUID().hashCode();
        Movie movie = inputMovie.create(id, movieName);
        Movies.put(id, movie);
    }

    /**
     * Puts movie to collection
     * @param key key to put movie in HashMap
     * @param movie movie object to be put in collection
     */
    public void putMovie(long key, Movie movie) {
        Movies.put(key, movie);
    }

    /**
     * Removes movie by id or key
     * @param id id of movie
     */
    public void removeMovie(Long id) {
        Movies.remove(id);
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
    public Set<Long> getKeySet() {
        return Movies.keySet();
    }

    /**
     * Returns movie by it's id
     * @param key id of movie
     * @return movie object
     */
    public Movie getValue(long key) {
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
        for (Map.Entry<Long, Movie> entry : Movies.entrySet()) {
            Movie movie = entry.getValue();
            moviesInfo = moviesInfo + "\n" + movie.getInfo();
        }
        return moviesInfo;
    }

    /**
     * Replaces movie by id
     * @param key id of movie to be replaced
     * @param newMovie movie that will replace old movie
     */
    public void replaceMovie(long key, Movie newMovie) {
        Movies.replace(key, newMovie);
    }

    /**
     * Returns movie
     * @param key id of movie
     * @return movie object
     */
    public Movie getMovie(long key) {
        return Movies.get(key);
    }

    /**
     * Returns this collection
     * @return collection with movies
     */
    public Map<Long, Movie> getMap() {
        return Movies;
    }

    public void newMovie(String name, String ID, String x, String y, String date, String oscars, String Length, String Genre, String rating, String nameDirecor, String birthday, String height, String weight, String xLocation, String yLocation, String zLocation) {
        Long id = Long.parseLong(ID.trim());
        int xCoordinate = Integer.parseInt(x.trim());
        float yCoordinate = Float.parseFloat(y.trim());
        int oscarCount = Integer.parseInt(oscars.trim());
        int lengthMovie = Integer.parseInt(Length.trim());
        Double heightPerson = Double.parseDouble(height.trim());
        float weightPerson = Float.parseFloat(weight.trim());
        int xLoc = Integer.parseInt(xLocation.trim());
        long yLoc = Long.parseLong(yLocation.trim());
        int zLoc = Integer.parseInt(zLocation.trim());
        Location location = new Location(xLoc, yLoc, zLoc);
        Movie movie = new Movie(id, name);
        movie.setCoordinates(xCoordinate,yCoordinate);
        movie.setGenre(Genre);
        movie.setMpaaRating(rating);
        movie.setOscarsCount(oscarCount);
        movie.setLength(lengthMovie);
        movie.setDirector(nameDirecor, heightPerson, weightPerson, location);
        Movies.put(id, movie);
    }
}
