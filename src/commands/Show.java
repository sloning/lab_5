package commands;

import data.MovieCollection;

/**
 * Prints all movies in collection
 *
 * @author Abay
 */
public class Show implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

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

    @Override
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        System.out.println(movieCollection.showMovies());
    }
}
