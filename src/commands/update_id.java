package commands;

import data.MovieCollection;

public class Update_id implements ICommand {
    public Update_id(){
        Commands.addNewCommand("update", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        Integer key = Integer.parseInt (parameter1.trim ());
        movieCollection.replaceMovie(key, parameter2);
    }
}
