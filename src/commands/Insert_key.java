package commands;

import data.MovieCollection;
import java.util.Scanner;

import input_output.InputMovie;
import movie.*;
import org.w3c.dom.ls.LSOutput;

public class Insert_key implements ICommand {
    public Insert_key(){
        Commands.addNewCommand("insert", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        Long key = Long.parseLong(parameter1.trim());
        movieCollection.putMovie(key, InputMovie.create(key, parameter2));

        System.out.println("В коллекцию успешно добавлен элемент " + parameter2 + " (Length " + parameter1 + ")");
    }

}
