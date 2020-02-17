package commands;

public class Save implements ICommand {
    public Save(){
        Commands.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
