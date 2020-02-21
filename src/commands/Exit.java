package commands;

/**
 * Stops program execution
 * @author Vladislav
 */
public class Exit implements ICommand {
    public Exit(){
        Commands.addNewCommand("exit", this);
    }

    @Override
    public void Do(String parameter1){
        System.exit(0);
    }
}
