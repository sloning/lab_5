package commands;

import data.MovieCollection;
import input_output.InputOutput;

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
