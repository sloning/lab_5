package commands;

import data.MovieCollection;
import movie.Movie;

import java.util.*;

/**
 * Count how many movies of this genre collection has
 * @author Vladislav
 */
public class Count_by_genre implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Count_by_genre(){
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
     * @param parameter1 name of genre
     */
    @Override
    public void Do(String parameter1) {
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
