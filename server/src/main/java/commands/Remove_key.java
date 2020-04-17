package src.main.java.commands;
import src.main.java.data.MovieCollection;

import java.io.IOException;
import java.util.Scanner;

/**
 * Removes movie by key
 *
 * @author Abay
 */
public class Remove_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

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
     * @param parameter1 key of movie to remove
     */
    @Override
    public void Do(String parameter1) throws IOException {
        if (parameter1 == null) {
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите ключ объекта, который хотите удалить");
            System.out.print("$");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {
                System.out.println("Ключ не может быть null");
            } else {
                Commands commands = new Commands(this.name, key);
            }
        } else {
            MovieCollection movieCollection = new MovieCollection();
            movieCollection.removeMovie(parameter1);
        }
    }
}
