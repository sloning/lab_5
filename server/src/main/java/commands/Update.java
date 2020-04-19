package commands;

import data.MovieCollection;
import movie.Movie;

import java.io.IOException;
import java.util.Scanner;

/**
 * Replace movie by it's id
 *
 * @author Abay
 */
public class Update implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Update() {
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
        return name + ": обновить значение элемента коллекции, ключ которого равен зададному";
    }

    /**
     * Replaces old movie by new movie
     *
     * @param parameter key to HashMap and id of new movie
     */
    @Override //Гениальный класс
    public String Do(String parameter, Movie movie) throws IOException {
            MovieCollection movieCollection = new MovieCollection();
            movieCollection.replaceMovie(parameter, movie);
        return "Объект по ключу " + parameter + " успешно обновлен";
    }
}