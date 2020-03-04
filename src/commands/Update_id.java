package commands;

import data.MovieCollection;
import input_output.InputMovie;

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
        return name + ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    /**
     * Replaces old movie by new movie
     * @param parameter1 key to HashMap and id of new movie
     */
    @Override //Гениальный класс
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        InputMovie newMovie = new InputMovie();
        movieCollection.replaceMovie(parameter1, newMovie.create());
    }
}
