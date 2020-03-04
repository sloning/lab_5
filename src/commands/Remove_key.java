package commands;

import data.MovieCollection;

/**
 * Removes movie by key
 * @author Abay
 */
public class Remove_key implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Remove_key(){
        name = "remove_key";
        Commands.addNewCommand("remove_key", this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": удалить элемент из коллекции по его ключу";
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
