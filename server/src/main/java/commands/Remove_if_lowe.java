package commands;
import Collection.MovieCollection;

import java.io.IOException;
import java.util.stream.Collectors;

import movie.*;
/**
 * Remove movie if it's id lower than my
 *
 * @author Vladislav
 */
public class Remove_if_lowe implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Remove_if_lowe() {
        name = "remove_lower_key";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": удалить значение по ключу, если новое значение меньше старого";
    }

    /**
     * Iterates through all elements of collection and removes movies by their id's
     *
     * @param parameter id, all movie's which has lower id's than this will be removed from collection
     */
    @Override
    public String Do(String parameter, Movie oldMovie) throws IOException {
            MovieCollection movieCollection = new MovieCollection();

            movieCollection.setMovies(movieCollection.getMovies().entrySet().stream()
                    .filter(p -> p.getValue().getId() >= Long.parseLong(parameter))
                    .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue())));

        return "Все значение, меньшие по ключу, чем новые, были удалены (если они были в коллекции)";
    }
}
