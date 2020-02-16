package commands;

public class Execute_script implements ICommand {
    public Execute_script(Commands com){
        com.addNewCommand("execute_script", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
    }
}
