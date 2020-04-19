package commands;

import data.MovieCollection;
import movie.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Count how many movies of this genre collection has
 *
 * @author Vladislav
 */
public class Count_by_genre implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Count_by_genre() {
        name = "count_by_genre";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    public String info() {
        return name + ": вывести количество элементов, значения поля genre которых равно заданому";
    }

    /**
     * Iterate through every element of collection to count all movies
     *
     * @param parameter name of genre
     */
    @Override
    public String Do(String parameter, Movie Movie) throws IOException {
        if (parameter == null) {
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите жанр");
            System.out.print("$");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {
                System.out.println("жанр не может быть null");
            } else {
                Commands commands = new Commands(this.name, key, Movie);
            }
        } else {
            int count = 0;
            MovieCollection movieCollection = new MovieCollection();
            Iterator it = movieCollection.getMap().entrySet().iterator();
            while (it.hasNext()) {
                Movie movie = (Movie) ((Map.Entry) it.next()).getValue();
                String movieGenre = movie.getGenre();
                if (parameter.equals(movieGenre)) {
                    count++;
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
            return "Количество фильмов жанра " + parameter + " - " + count;
        }
        return null;
    }
}
