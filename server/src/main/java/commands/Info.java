package commands;

import Collection.MovieCollection;
import movie.Movie;

/**
 * SHows info about collection
 *
 * @author Abay
 */
public class Info implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Info() {
        name = "info";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.";
    }

    /**
     * Prints info about collection
     *
     * @param parameter nothing here
     */
    @Override
    public String Do(String parameter, Movie movie) {
        MovieCollection movieCollection = new MovieCollection();
        String result = "тип коллекции: LinkedHashMap\n";
        result += "количество элементов коллекции: " + movieCollection.getMovies().entrySet().stream().count() +"\n";
        result += "дата создания колекции: " + movieCollection.getDateCreation();
        return result;
    }
}
