package commands;

import data.MovieCollection;

/**
 * Remove movie if it's id lower than my
 * @author Vladislav
 */
public class Remove_if_lowe implements ICommand {
    public Remove_if_lowe(){
        Commands.addNewCommand("remove_lower_key", this);
    }

    /**
     * Uses for loop and removes movies by their id's
     * @param key id, all movie's which has lower id's than this will be removed from collection
     * @param parameter2 just ignore this
     */
    @Override
    public void Do(String key, String parameter2) {
        MovieCollection movieCollection = new MovieCollection();
        for (long keyToRemove = 0; keyToRemove < Integer.parseInt(key); keyToRemove++) {
            movieCollection.removeMovie(keyToRemove);
        }
    }
}
