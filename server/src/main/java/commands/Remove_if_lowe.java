package commands;

import Collection.MovieCollection;
import movie.Movie;

import java.util.Map;
import java.util.stream.Collectors;
/**
 * Remove movie if it's id lower than my
 *
 * @author Vladislav
 */
public class Remove_if_lowe implements ICommand {
    /**
     * @param name name of command
     */
    private final String name;

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
    public String Do(String parameter, Movie oldMovie, String user) {
        MovieCollection movieCollection = new MovieCollection();
        if (movieCollection.getMovies().entrySet().stream()
                .filter(p -> p.getValue().getId() >= Long.parseLong(parameter))
                .anyMatch(p -> p.getValue().getUser().equals(user))) {
            movieCollection.setMovies(movieCollection.getMovies().entrySet().stream()
                    .filter(p -> p.getValue().getUser().equals(user))
                    .filter(p -> p.getValue().getId() >= Long.parseLong(parameter))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
            return "Все значение, меньшие по ключу, чем новые, были удалены (если они были в коллекции)";
        } else {
            return "Вам не принадлежит ни 1 фильм, подходящий под условие";
        }
    }
}
