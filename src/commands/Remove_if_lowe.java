package commands;

import movie.MovieCollection;

public class Remove_if_lowe implements ICommand {
    public Remove_if_lowe(Commands com){
        com.addNewCommand("remove_lower_key", this);
    }

    @Override
    public void Do(String key, String parameter2) {
        for (long keyToRemove = 0; keyToRemove < Integer.parseInt(key); keyToRemove++) {
            MovieCollection.removeMovie(keyToRemove);
        }
    }
}
