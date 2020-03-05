package commands;

import data.MovieCollection;
import input_output.FabricOfMovies;

/**
 * Replace movie by it's id
 * @author Abay
 */
public class Update_id implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Update_id(){
        name = "update";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": обновить значение элемента коллекции, ID которого равен зададному";
    }

    /**
     * Replaces old movie by new movie
     * @param parameter1 key to HashMap and id of new movie
     */
    @Override //Гениальный класс
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        FabricOfMovies newMovie = new FabricOfMovies();
        movieCollection.replaceMovie(parameter1, newMovie.create());
    }
}