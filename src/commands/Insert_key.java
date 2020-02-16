package commands;

public class Insert_key implements ICommand {
    public Insert_key(Commands com){
        com.addNewCommand("insert", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
