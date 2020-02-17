package commands;

import data.MovieCollection;

public class Info implements ICommand {
    public Info(){
        Commands.addNewCommand("info", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.getInfo();
    }
}
