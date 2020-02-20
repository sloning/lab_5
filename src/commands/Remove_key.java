package commands;

import data.MovieCollection;

/**
 * Removes movie by key
 * @author Abay
 */
public class Remove_key implements ICommand {
    public Remove_key(){
        Commands.addNewCommand("add", this);
    }

    /**
     * Removes movie by key
     * @param parameter1 key of movie to remove
     * @param parameter2 nothing
     */
    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection movieCollection = new MovieCollection();
        Long key = Long.parseLong(parameter1.trim());
        movieCollection.removeMovie(key);
    }
}
