package commands;

import data.MovieCollection;
import movie.Movie;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

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
     * @param parameter1 id, all movie's which has lower id's than this will be removed from collection
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
            Iterator it = movieCollection.getMap().entrySet().iterator();
            long givenId = Long.parseLong(parameter1);
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Movie movie = (Movie) pair.getValue();
                long currentId = movie.getId();
                if (currentId < givenId) {
                    it.remove(); // avoids a ConcurrentModificationException
                    movieCollection.removeMovie((String) pair.getKey());
                }
            }
        }
    }
}
