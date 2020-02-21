package commands;

import data.CommandHistory;

/**
 * Prints last six commands
 * @author People of the Earth
 */
public class History implements ICommand {
    public History(){
        Commands.addNewCommand("history", this);
    }

    @Override
    public void Do(String parameter1){
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.printHistory();
    }

}
