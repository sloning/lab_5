package commands;

import movie.MovieCollection;

public class Remove_key implements ICommand {
    public Remove_key(Commands com){
        com.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        Long key = Long.parseLong (parameter1.trim ());
        MovieCollection.removeMovie(key);
    }
}
