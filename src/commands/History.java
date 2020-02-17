package commands;

public class History implements ICommand {
    public History(){
        Commands.addNewCommand("history", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }

}
