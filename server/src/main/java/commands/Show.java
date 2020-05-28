package commands;

import Collection.MovieCollection;
import movie.Movie;

import java.util.Comparator;

/**
 * Prints all movies in collection
 *
 * @author Abay
 */
public class Show implements ICommand {
    /**
     * @param name name of command
     */
    private final String name;

    public Show() {
        name = "show";
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

    class ShowInfo {
        private String info = "";

        public void addInfo(String info) {
            this.info += info;
        }

        public String getInfo() {
            return info;
        }
    }

    @Override
    public String Do(String parameter, Movie movie, String user) {
        MovieCollection movieCollection = new MovieCollection();
        ShowInfo showInfo = new ShowInfo();
        movieCollection.getMovies().entrySet().stream().sorted(Comparator.comparing(p -> p.getValue().getName())).forEach(x -> showInfo.addInfo("\n" + x.getValue().getInfo() + "Movie Key: " + x.getKey() + "\n"));
        return showInfo.getInfo();
    }
}
