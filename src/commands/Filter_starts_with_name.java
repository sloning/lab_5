package commands;

public class Filter_starts_with_name implements ICommand {
    public Filter_starts_with_name(){
        Commands.addNewCommand("filter_starts_with_name", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
