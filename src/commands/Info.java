package commands;

import data.MovieCollection;

/**
 * SHows info about collection
 * @author Abay
 */
public class Info implements ICommand {
    public Info(){
        Commands.addNewCommand("info", this);
    }

    /**
     * Prints info about collection
     * @param parameter1 nothing here
     */
    @Override
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.getInfo();
    }
}
