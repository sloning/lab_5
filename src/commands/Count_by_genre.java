package commands;

import data.MovieCollection;
import movie.Movie;

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
     * @param parameter1 name of genre
     */
    @Override
    public void Do(String parameter1) throws IOException {
        if (parameter1 == null) {
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите жанр");
            System.out.print("$");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {
                System.out.println("жанр не может быть null");
            } else {
                Commands commands = new Commands(this.name, key);
            }
        } else {
            int count = 0;
            MovieCollection movieCollection = new MovieCollection();
            Iterator it = movieCollection.getMap().entrySet().iterator();
            while (it.hasNext()) {
                Movie movie = (Movie) ((Map.Entry) it.next()).getValue();
                String movieGenre = movie.getGenre();
                if (parameter1.equals(movieGenre)) {
                    count++;
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
            System.out.print("Количество фильмов жанра " + parameter1 + " - ");
            System.out.println(count);
        }
    }
}
