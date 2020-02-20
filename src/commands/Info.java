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
     * @param parameter2 ignore this parameter
     */
    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.getInfo();
    }
}
