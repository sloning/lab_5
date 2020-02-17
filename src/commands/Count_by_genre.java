package commands;

public class Count_by_genre implements ICommand {
    public Count_by_genre(){
        Commands.addNewCommand("count_by_genre", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
