package commands;

import data.MovieCollection;
import input_output.SaveMovies;

import java.io.IOException;

/**
 * Saves collection to a file
 */
public class Save implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Save(){
        name = "save";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ":  сохранить коллекцию в файл";
    }

    @Override
    public void Do(String parameter1) throws IOException {
        MovieCollection movieCollection = new MovieCollection();
        SaveMovies save = new SaveMovies();
        save.getOut();
    }
}
