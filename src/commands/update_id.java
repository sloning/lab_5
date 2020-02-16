package commands;

public class Update_id implements ICommand {
    public Update_id(Commands com){
        com.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
