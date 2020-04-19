package commands;

import data.MovieCollection;
import movie.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Prints all movies which names starts with this name
 *
 * @author Vladislav
 */
public class Filter_starts_with_name implements ICommand {
    /**
     * @param name name of command
     */
    private String name;


    public Filter_starts_with_name() {
        name = "filter_starts_with_name";
        Commands.addNewCommand(name, this);
    }

    /**
     * Iterates through all elements of collection and print elements which names starts with this name
     *
     * @param parameter name to filter
     */
    @Override
    public String Do(String parameter, Movie Movie) throws IOException {
            MovieCollection movieCollection = new MovieCollection();
            Iterator it = movieCollection.getMap().entrySet().iterator();
            while (it.hasNext()) {
                Movie movie = (Movie) ((Map.Entry) it.next()).getValue();
                String movieName = movie.getName();
                String result=null;
                if (movieName.substring(0, parameter.length()).compareTo(parameter) == 0) {
                    result += movieName;
                }
                it.remove(); // avoids a ConcurrentModificationException
                return result;
            }
            return null;
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
