package commands;

import input_output.InputOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Executes script
 * @author Abay geniy
 */
public class Execute_script implements ICommand {
    /**
     * @param name name of command
     */
    private String name;
    private List<String> fileNames = new ArrayList<>();

    public Execute_script() {
        name = "execute_script";
        Commands.addNewCommand("execute_script", this);
    }

    @Override
    public void Do(String parameter1) throws IOException {
        if (parameter1 == null){
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите название файла");
            System.out.print("$ ");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {System.out.println("Название файла не может быть null");}
            else {Commands commands = new Commands(this.name, key);}
        } else {
            try {
                InputOutput inputOutput = new InputOutput();
                if (fileNames.contains(parameter1)) {
                    System.err.println("STACKOVERFLOW");
                } else {
                    fileNames.add(parameter1);
                    inputOutput.InputFile(parameter1);
                }
            } catch (StackOverflowError e) {
                System.out.println("STACKOVERFLOW");
                return;
            }
        }
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