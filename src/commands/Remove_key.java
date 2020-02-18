package commands;

import data.MovieCollection;

public class Remove_key implements ICommand {
    public Remove_key(){
        Commands.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        Integer key = Integer.parseInt (parameter1.trim ());
        movieCollection.removeMovie(key);
    }
}