package commands;

import data.MovieCollection;
import movie.Movie;
import input_output.InputMovie;

/**
 * Replace element if it new element has lower length
 * @author Vladislav
 */
public class Replace_if_lowe implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Replace_if_lowe() {
        name = "replace_if_lower";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": заменить значение по ключу, если новое значение меньше старого";
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
