package commands;

import data.MovieCollection;

import java.util.Collections;

/**
 * Printing element with minimal id
 * @author Vladislav
 */
public class Min_by_id implements ICommand {
    public Min_by_id() {
        Commands.addNewCommand("min_by_id", this);
    }

    /**
     * Get Key Set from HashMap then take lowest key (key = id) and print it's value
     * @param parameter1 ignore this
     */
    @Override
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection(); //something wrong.
        System.out.println(movieCollection.getValue(Collections.min(movieCollection.getKeySet())));
    }
}
