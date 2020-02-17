package commands;

import data.MovieCollection;

public class Remove_if_lowe implements ICommand {
    public Remove_if_lowe(){
        Commands.addNewCommand("remove_lower_key", this);
    }

    @Override
    public void Do(String key, String parameter2) {
        MovieCollection movieCollection = new MovieCollection();
        for (int keyToRemove = 0; keyToRemove < Integer.parseInt(key); keyToRemove++) {
            movieCollection.removeMovie(keyToRemove);
        }
    }
}
