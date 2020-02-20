package commands;

import data.MovieCollection;
import movie.Movie;
import input_output.InputMovie;

/**
 * Replace element if it new element has lower length
 * @author Vladislav
 */
public class Replace_if_lowe implements ICommand {
    public Replace_if_lowe() {
        Commands.addNewCommand("replace_if_lower", this);
    }

    /**
     * Get new movie object and compare's it to old movie
     * @param parameter1 key to HashMap
     * @param parameter2 name of new movie
     */
    @Override
    public void Do(String parameter1, String parameter2) {
        MovieCollection movieCollection = new MovieCollection();
        Long key = Long.parseLong(parameter1.trim());
        Movie newMovie = InputMovie.create(key, parameter2);
        Movie oldMovie = movieCollection.getMovie(key);

        if (newMovie.getLength() < oldMovie.getLength()) {
            movieCollection.replaceMovie(key, newMovie);
        }
    }
}
