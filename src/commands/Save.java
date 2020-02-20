package commands;

import data.MovieCollection;
import input_output.InputOutput;

public class Save implements ICommand {
    public Save(){
        Commands.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        InputOutput inputOutput = new InputOutput();
        inputOutput.Output(movieCollection.getMap());
    }
}
