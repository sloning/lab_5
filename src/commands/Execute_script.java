package commands;

import input_output.InputOutput;

import java.io.IOException;

/**
 * Executes script
 * @author Not Yet
 */
public class Execute_script implements ICommand {
    /**
     * @param name name of command
     */
    private String name;

    public Execute_script() {
        name = "execute_script";
        Commands.addNewCommand("execute_script", this);
    }

    @Override
    public void Do(String parameter1) throws IOException {
        InputOutput inputOutput = new InputOutput();
        inputOutput.InputFile(parameter1);
    }

    /**
     * get info about command
     *
     * @return String
     */
    public String info() {
        return name + ": считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }
}