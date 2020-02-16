package commands;

public class Min_by_id implements ICommand {
    public Min_by_id(Commands com){
        com.addNewCommand("Min_by_id", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
