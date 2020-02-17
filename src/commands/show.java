package commands;

import data.MovieCollection;
import movie.Movie;

public class Show implements ICommand {
    public Show(){
        Commands.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2) {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.showMovie();
    }
}
