package commands;

import movie.MovieCollection;

import java.util.Collections;

public class Min_by_id implements ICommand {
    public Min_by_id(Commands com){
        com.addNewCommand("min_by_id", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        System.out.println(MovieCollection.getValue(Collections.min(MovieCollection.getKeySet())));
    }
}
