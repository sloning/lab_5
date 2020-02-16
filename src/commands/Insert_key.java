package commands;
import movie.*;
public class Insert_key implements ICommand {
    public Insert_key(Commands com){
        com.addNewCommand("insert", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        Long key = Long.parseLong (parameter1.trim ());
        MovieCollection.addMovie(key, parameter2);
    }
}
