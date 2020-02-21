package commands;
import data.MovieCollection;

/**
 * Removes all movies from collection
 * @author Abay
 */
public class Clear implements ICommand{
    public Clear(){
        Commands.addNewCommand("clear", this);
    }

    /**
     * @param parameter1 ignore this
     */
    @Override
    public void Do(String parameter1) {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.clearMovies();
    }
}
