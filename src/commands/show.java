package commands;

public class Show implements ICommand {
    public Show(Commands com){
        com.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
