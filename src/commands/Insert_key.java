package commands;

import data.MovieCollection;
import input_output.FabricOfMovies;

import java.io.IOException;
import java.util.Scanner;

/**
 * Insert movie by key
 * @author Abay
 */
public class Insert_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Insert_key(){
        name = "insert";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": добавить новый элемент с заданным ключом";
    }


    /**
     * Replaces movie by id
     * @param parameter1 key to HashMap to insert movie
     */
    @Override
    public void Do(String parameter1) throws IOException { //TODO Работает даже когда ключ не введён из-за этого вместо ключа будет null
        if (parameter1 == null){
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите ключ");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {System.out.println("Ключ не может быть null");}
            else {Commands commands = new Commands(this.name, key);}
        } else {
            MovieCollection movieCollection = new MovieCollection();
            FabricOfMovies newMovie = new FabricOfMovies();
            movieCollection.putMovie(parameter1, newMovie.create());

            System.out.println("В коллекцию успешно добавлен элемент " + newMovie.getName());
        }
    }

}
