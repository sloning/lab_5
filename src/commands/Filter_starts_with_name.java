package commands;

import data.MovieCollection;
import movie.Movie;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Prints all movies which names starts with this name
 * @author Vladislav
 */
public class Filter_starts_with_name implements ICommand {
    /**
     * @param name name of command
     */
    private String name;


    public Filter_starts_with_name() {
        name = "filter_starts_with_name";
        Commands.addNewCommand(name, this);
    }

    /**
     * Iterates through all elements of collection and print elements which names starts with this name
     * @param parameter1 name to filter
     */
    @Override
    public void Do(String parameter1) throws IOException {
        if (parameter1 == null){
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите подстроку");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {System.out.println("подстрока не может быть null");}
            else {Commands commands = new Commands(this.name, key);}
        } else {
            MovieCollection movieCollection = new MovieCollection();
            Iterator it = movieCollection.getMap().entrySet().iterator();
            while (it.hasNext()) {
                Movie movie = (Movie) ((Map.Entry) it.next()).getValue();
                String movieName = movie.getName();
                if (movieName.substring(0, parameter1.length()).compareTo(parameter1) == 0) {
                    System.out.println(movieName);
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
