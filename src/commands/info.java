package commands;

import movie.MovieCollection;

public class Info implements ICommand {
    public Info(Commands com){
        com.addNewCommand("info", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection.getInfo();
    }
}
