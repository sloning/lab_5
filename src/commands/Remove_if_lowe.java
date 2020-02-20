package commands;

import data.MovieCollection;

import java.util.Iterator;

/**
 * Remove movie if it's id lower than my
 * @author Vladislav
 */
public class Remove_if_lowe implements ICommand {
    public Remove_if_lowe(){
        Commands.addNewCommand("remove_lower_key", this);
    }

    /**
     * Iterates through all elements of collection and removes movies by their id's
     * @param key id, all movie's which has lower id's than this will be removed from collection
     * @param parameter2 just ignore this
     */
    @Override
    public void Do(String key, String parameter2) {
        MovieCollection movieCollection = new MovieCollection();
        Iterator it = movieCollection.getKeySet().iterator();
        long givenId = Long.parseLong(key);
        while (it.hasNext()) {
            long currentId = (long) it.next();
            System.out.println(currentId);
            if (currentId < givenId) {
                it.remove(); // avoids a ConcurrentModificationException
                movieCollection.removeMovie(currentId);
            }
        }
    }
}
