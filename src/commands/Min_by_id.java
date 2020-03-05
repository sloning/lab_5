package commands;

import data.MovieCollection;

import java.util.Collections;

/**
 * Printing element with minimal id
 * @author Vladislav
 */
public class Min_by_id implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Min_by_id() {
        name = "min_by_id";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести любой объект из коллекции, значение поля id которого является минимальным";
    }

    /**
     * Get Key Set from HashMap then take lowest key (key = id) and print it's value
     * @param parameter1 ignore this
     */
    @Override
    public void Do(String parameter1) { //TODO по прежнему сортирует по ключу мапы НЕ РАБОТАЕТ
        MovieCollection movieCollection = new MovieCollection();
        System.out.println(movieCollection.getKeySet());
        System.out.println(movieCollection.getValue(Collections.min(movieCollection.getKeySet())));
    }
}
