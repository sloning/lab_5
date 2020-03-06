package commands;

import data.MovieCollection;
import input_output.FabricOfMovies;
import input_output.InputOutput;
import movie.Location;
import movie.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Insert movie by key
 *
 * @author Abay
 */
public class Insert_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Insert_key() {
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
     *
     * @param parameter1 key to HashMap to insert movie
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
            if (Execute_script.getSignal() == 0) {
                FabricOfMovies newMovie = new FabricOfMovies();
                newMovie.create();
            } else {
                int c = InputOutput.count;
                FileReader fileReader = new FileReader(Execute_script.getFileName());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (c-- > 0) {
                    bufferedReader.readLine();
                }
                Movie movie = new Movie(); //TODO исправить дату
                movie.setName(bufferedReader.readLine());
                movie.setCoordinates(Integer.parseInt(bufferedReader.readLine()), Float.parseFloat(bufferedReader.readLine()));
                movie.setOscarsCount(Integer.parseInt(bufferedReader.readLine()));
                movie.setLength(Integer.parseInt(bufferedReader.readLine()));
                movie.setGenre(bufferedReader.readLine());
                movie.setMpaaRating(bufferedReader.readLine());
                movie.setDirector(bufferedReader.readLine(), Double.parseDouble(bufferedReader.readLine()), Float.parseFloat(bufferedReader.readLine()), new Location(bufferedReader.readLine(), Integer.parseInt(bufferedReader.readLine()), Long.parseLong(bufferedReader.readLine()), Integer.parseInt(bufferedReader.readLine())));
                movieCollection.putMovie(parameter1, movie);

                System.out.println("В коллекцию успешно добавлен элемент " + movie.getName());
            }
        }
    }
}
