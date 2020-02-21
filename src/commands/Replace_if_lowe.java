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
     */
    @Override
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        InputMovie inputMovie = new InputMovie();
        Movie newMovie = inputMovie.create();
        Movie oldMovie = movieCollection.getMovie(parameter1);

        if (newMovie.getLength() < oldMovie.getLength()) {
            movieCollection.replaceMovie(parameter1, newMovie);
        }
    }
}
