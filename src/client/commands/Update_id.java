package client.commands;

import server.data.MovieCollection;
import client.input_output.FabricOfMovies;

import java.io.IOException;
import java.util.Scanner;

/**
 * Replace server.movie by it's id
 *
 * @author Abay
 */
public class Update_id implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Update_id() {
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
        return name + ": обновить значение элемента коллекции, ID которого равен зададному";
    }

    /**
     * Replaces old server.movie by new server.movie
     *
     * @param parameter1 key to HashMap and id of new server.movie
     */
    @Override //Гениальный класс
    public void Do(String parameter1) throws Exception {
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
            FabricOfMovies newMovie = new FabricOfMovies();
            movieCollection.replaceMovie(parameter1, newMovie.create());
        }
    }
}