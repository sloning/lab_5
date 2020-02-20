package commands;

import data.MovieCollection;
import input_output.InputMovie;

/**
 * Insert movie by key
 * @author Abay
 */
public class Insert_key implements ICommand {
    public Insert_key(){
        Commands.addNewCommand("insert", this);
    }

    /**
     * Replaces movie by id
     * @param parameter1 key to HashMap to insert movie
     * @param parameter2 name of new movie
     */
    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        Long key = Long.parseLong(parameter1.trim());
        InputMovie newMovie = new InputMovie();
        movieCollection.putMovie(key, newMovie.create(key, parameter2));

        System.out.println("В коллекцию успешно добавлен элемент " + parameter2 + " (Length " + parameter1 + ")");
    }

}
