package commands;

import Collection.MovieCollection;
import movie.Movie;

import java.io.IOException;

/**
 * Removes movie by key
 *
 * @author Abay
 */
public class Remove_key implements ICommand {
    /**
     * @param name name of command
     */
    private final String name;

    public Remove_key() {
        name = "remove";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": удалить элемент из коллекции по его ключу";
    }


    /**
     * Removes movie by key
     *
     * @param parameter key of movie to remove
     */
    @Override
    public String Do(String parameter, Movie movie, String user) throws IOException {
       try {
           MovieCollection movieCollection = new MovieCollection();
           if (movieCollection.getMovie(parameter).getUser().equals(user)) {
                movieCollection.getMovies().remove(parameter);
               return "Фильм успешо удалён";
           } else {
               return "Вы не можете удалить этот фильм, поскольку он вам не принадлежит";
           }
       } catch (NullPointerException e) {
           return "В коллекции нет фильма с таким ключом";
       }
    }
}
