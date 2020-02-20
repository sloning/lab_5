package commands;

import data.MovieCollection;

/**
 * Prints all movies in collection
 * @author Abay
 */
public class Show implements ICommand {
    public Show(){
        Commands.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2) {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.showMovies();
    }
}
