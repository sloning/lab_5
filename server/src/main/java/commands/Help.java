package server.src.main.java.commands;

/**
 * Prints help for every command
 *
 * @author Abay
 */
public class Help implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Help() {
        name = "help";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести справку по доступным командам";
    }

    @Override
    public void Do(String parameter1) {
        Commands.help();
    }
}
