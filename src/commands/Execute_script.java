package commands;

public class Execute_script implements ICommand {
    public Execute_script(){
        Commands.addNewCommand("execute_script", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
    }
}
