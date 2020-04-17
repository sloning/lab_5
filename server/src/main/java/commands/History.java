package src.main.java.commands;
//TODO этот класс я так понимаю нужно убрать или перенести в клиент
/**
 * Prints last six commands
 *
 * @author People of the Earth
 */
public class History implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public History() {
        name = "history";
        Commands.addNewCommand(name, this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": вывести последние 6 команд (без их аргументов)";
    }

    @Override
    public void Do(String parameter1) {
//        CommandHistory commandHistory = new CommandHistory();
//        commandHistory.printHistory();
    }

}
