package commands;

import data.MovieCollection;
import input_output.GoOUT;

import java.io.IOException;

/**
 * Saves collection to a file
 */
public class Save implements ICommand {
    public Save(){
        Commands.addNewCommand("save", this);
    }

    @Override
    public void Do(String parameter1, String parameter2) throws IOException {
        MovieCollection movieCollection = new MovieCollection();
        GoOUT go = new GoOUT();
        go.getOut();
    }
}
