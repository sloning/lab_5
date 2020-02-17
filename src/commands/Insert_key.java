package commands;
import data.MovieCollection;
import java.util.Scanner;
public class Insert_key implements ICommand {
    public Insert_key(){
        Commands.addNewCommand("insert", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        Integer key = Integer.parseInt (parameter1.trim ());
        movieCollection.addMovie(key, parameter2);
        System.out.println("Введите координаты");

        System.out.println("В коллекцию успешно добавлен элемент " + parameter2 + " (Length " + parameter1 + ")");
    }
}
