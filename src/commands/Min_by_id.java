package commands;

import data.MovieCollection;

import java.util.Collection;
import java.util.Collections;

public class Min_by_id implements ICommand {
    private MovieCollection mv;
    public Min_by_id(MovieCollection mc){
        mv = new MovieCollection();
        Commands.addNewCommand("min_by_id", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection(); //something wrong.
        System.out.println(movieCollection.getValue(Collections.min(movieCollection.getKeySet())));
    }
}
