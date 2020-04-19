package commands;

import data.MovieCollection;
import input_output.FabricOfMovies;
import movie.Movie;

import java.io.IOException;

/**
 * Insert movie by key
 *
 * @author Abay
 */
public class Insert_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Insert_key() {
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
     *
     * @param parameter key to HashMap to insert movie
     */
    @Override
    public String Do(String parameter, Movie movie) throws IOException {
        String key = parameter;
//        } else {
            MovieCollection movieCollection = new MovieCollection();
//            if (!Execute_script.getSignal()) {
                movieCollection.putMovie(key, movie);

                return "В коллекцию успешно добавлен фильм " + movie.getName();
//            } else {

//
//                System.out.println("В коллекцию успешно добавлен элемент " + movie.getName());
//            }
//        }
    }
}
