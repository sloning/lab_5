package commands;

import data.MovieCollection;
import movie.Movie;
import movie.MovieGenre;

import java.util.*;

public class Count_by_genre implements ICommand {
    public Count_by_genre(){
        Commands.addNewCommand("count_by_genre", this);
    }

    @Override
    public void Do(String parameter1, String parameter2) {      //Да я хуй знает как это делать
        int count = 0;
        MovieCollection movieCollection = new MovieCollection();
        Iterator it = movieCollection.getMap().entrySet().iterator();
        while (it.hasNext()) {
            Movie movie = (Movie) ((Map.Entry) it.next()).getValue();
            String movieGenre = movie.getGenre();
            if (parameter1 == movieGenre) {
                count++;
            }
            it.remove(); // avoids a ConcurrentModificationException TODO прочитать про это
        }
        System.out.print("Количество фильмов жанра " + parameter1 + " - ");
        System.out.println(count);
    }
}
