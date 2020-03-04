package commands;

import data.MovieCollection;
import input_output.InputMovie;

/**
 * Insert movie by key
 * @author Abay
 */
public class Insert_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Insert_key(){
        name = "insert";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": добавить новый элемент с заданным ключом";
    }


    /**
     * Replaces movie by id
     * @param parameter1 key to HashMap to insert movie
     */
    @Override
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        InputMovie newMovie = new InputMovie();
        movieCollection.putMovie(parameter1, newMovie.create());

        System.out.println("В коллекцию успешно добавлен элемент " + newMovie.getName());
    }

}
