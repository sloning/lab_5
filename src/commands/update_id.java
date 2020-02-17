package commands;

import movie.MovieCollection;

public class Update_id implements ICommand {
    public Update_id(){
        Commands.addNewCommand("update", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        Long key = Long.parseLong (parameter1.trim ());
        MovieCollection.replaceMovie(key, parameter2);
    }
}
