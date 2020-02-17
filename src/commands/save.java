package commands;

import data.MovieCollection;
import input_output.InputOutput;
import movie.Movie;

import java.util.Collection;

public class Save implements ICommand {
    public Save(){
        Commands.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        InputOutput inputOutput = new InputOutput();
        MovieCollection movieCollection = new MovieCollection();
    }
}
