package commands;

import data.MovieCollection;

import java.util.Collections;

public class Min_by_id implements ICommand {
    public Min_by_id(){
        Commands.addNewCommand("min_by_id", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        //System.out.println(movieCollection.getValue(Collections.min(movieCollection.getKeySet())));
    }
}
