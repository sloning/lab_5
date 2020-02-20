package commands;

import data.CommandHistory;

public class History implements ICommand {
    public History(){
        Commands.addNewCommand("history", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.printHistory();
    }

}
