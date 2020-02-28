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
     * TODO Не работает из-за того, что ключ хешмапы больше не равен id
     * Removes movie by key
     * @param parameter1 key of movie to remove
     */
    @Override
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.removeMovie(parameter1);
    }
}
