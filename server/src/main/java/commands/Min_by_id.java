package commands;

import data.MovieCollection;
import movie.Movie;

/**
 * Printing element with minimal id
 *
 * @author Vladislav
 */
public class Min_by_id implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Min_by_id() {
        name = "min_by_id";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести любой объект из коллекции, значение поля id которого является минимальным";
    }

    /**
     * Get Key Set from HashMap then take lowest key (key = id) and print it's value
     *
     * @param parameter ignore this
     */
    @Override
    public String Do(String parameter, Movie movie) {
        MovieCollection movieCollection = new MovieCollection();
        return movieCollection.getMinimumId();
    }

    //TODO добавить исключение если не будет элементов в коллекции
}
