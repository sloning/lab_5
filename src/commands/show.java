package commands;

import movie.MovieCollection;

public class Show implements ICommand {
    public Show(){
        Commands.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection.showMovie();
    }
}
