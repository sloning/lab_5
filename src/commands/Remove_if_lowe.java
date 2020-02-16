package commands;

public class Remove_if_lowe implements ICommand {
    public Remove_if_lowe(Commands com){
        com.addNewCommand("add", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){

    }
}
