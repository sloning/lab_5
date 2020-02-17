package commands;

public class Replace_if_lowe implements ICommand {
    public Replace_if_lowe(Commands com){
        com.addNewCommand("replace_if_lower", this);
    }

    @Override
    public void Do(String parameter1, String parameter2) {

    }
}
