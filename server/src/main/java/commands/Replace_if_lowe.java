package src.main.java.commands;
import src.main.java.data.MovieCollection;

import java.io.IOException;
import java.util.Scanner;
import src.main.java.movie.*;
/**
 * Replace element if it new element has lower length
 *
 * @author Vladislav
 */
public class Replace_if_lowe implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Replace_if_lowe() {
        name = "replace_if_lowe";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": заменить значение по ключу, если новое значение меньше старого";
    }

    /**
     * Get new movie object and compare's it to old movie
     *
     * @param parameter1 key to HashMap
     */
    @Override
    public void Do(String parameter1) throws IOException {
        if (parameter1 == null) {
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите ключ");
            System.out.print("$");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {
                System.out.println("Ключ не может быть null");
            } else {
                Commands commands = new Commands(this.name, key);
            }
        } else {
            MovieCollection movieCollection = new MovieCollection();
            //FabricOfMovies fabricOfMovies = new FabricOfMovies();
            //Movie newMovie = fabricOfMovies.create();
            //Movie oldMovie = movieCollection.getMovie(parameter1);

            //if (newMovie.getLength() < oldMovie.getLength()) {
            //    movieCollection.replaceMovie(parameter1, newMovie);
            //}
            //TODO переделать эту команду
        }
    }
}
