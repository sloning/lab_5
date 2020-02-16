package commands;
import movie.*;
public class Clear implements ICommand{
    public Clear(Commands com){
        com.addNewCommand("clear", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        MovieCollection.clearMovies();
    }
}
