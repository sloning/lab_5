package commands;

import data.MovieCollection;

/**
 * Prints all movies in collection
 * @author Abay
 */
public class Show implements ICommand {
    public Show(){
        Commands.addNewCommand("show", this);
    }

    @Override
    public void Do(String parameter1, String parameter2) {
        MovieCollection movieCollection = new MovieCollection();
        System.out.println(movieCollection.showMovies());
    }
}
