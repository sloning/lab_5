package commands;

import data.MovieCollection;
import movie.Movie;

import java.util.*;

/**
 * Count how many movies of this genre collection has
 * @author Vladislav
 */
public class Count_by_genre implements ICommand {
    public Count_by_genre(){
        Commands.addNewCommand("count_by_genre", this);
    }

    /**
     * Iterate through every element of collection to count all movies
     * @param parameter1 name of genre
     * @param parameter2 nothing here, ignore it
     */
    @Override
    public void Do(String parameter1, String parameter2) {
        /**
         * Counts how many movies of the genre
         */
        int count = 0;
        MovieCollection movieCollection = new MovieCollection();
        Iterator it = movieCollection.getMap().entrySet().iterator();
        while (it.hasNext()) {
            Movie movie = (Movie) ((Map.Entry) it.next()).getValue();
            String movieGenre = movie.getGenre();
            if (parameter1 == movieGenre) {
                count++;
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        System.out.print("Количество фильмов жанра " + parameter1 + " - ");
        System.out.println(count);
    }
}
