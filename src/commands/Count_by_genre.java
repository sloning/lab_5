package commands;

public class Count_by_genre implements ICommand {
    public Count_by_genre(Commands com){
        com.addNewCommand("Count_by_genre", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
