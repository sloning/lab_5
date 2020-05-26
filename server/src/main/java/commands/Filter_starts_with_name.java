package commands;

import Collection.MovieCollection;
import movie.*;
import java.io.IOException;
import java.util.Comparator;

/**
 * Prints all movies which names starts with this name
 *
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

    class ShowInfo{
        private String info = "";

        public void addInfo(String info){
            this.info += info;
        }

        public String getInfo(){
            return info;
        }
    }

    /**
     * Iterates through all elements of collection and print elements which names starts with this name
     *
     * @param parameter name to filter
     */
    @Override
    public String Do(String parameter, Movie Movie, String user) throws IOException {
            MovieCollection movieCollection = new MovieCollection();
            ShowInfo showInfo = new ShowInfo();
            movieCollection.getMovies().entrySet().stream()
                    .filter(p -> p.getValue().getName().substring(0, parameter.length()).compareTo(parameter) == 0)
                    .sorted(Comparator.comparing(p -> p.getValue().getName()))
                    .forEach(x -> showInfo.addInfo("\n" + x.getValue().getInfo() + "\nMovie Key: " + x.getKey()));;

            return showInfo.getInfo();
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
