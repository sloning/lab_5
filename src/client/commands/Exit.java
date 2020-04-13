package client.commands;

import java.io.Serializable;

/**
 * Stops program execution
 *
 * @author Vladislav
 */
public class Exit implements ICommand, Serializable {
    /**
     * @param name name of command
     */
    private String name;

    public Exit() {
        name = "exit";
        Commands.addNewCommand("exit", this);
    }

    /**
     * get info about command
     *
     * @return String
     */
    @Override
    public String info() {
        return name + ": завершить программу (без сохранения в файл)";
    }

    @Override
    public void Do(String parameter1) {
        System.exit(0);
    }
}
