package src.main.java.commands;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Executes script
 *
 * @author Abay geniy
 */
public class Execute_script implements ICommand {
    /**
     * @param name name of command
     */
    private static String fileName;
    private static boolean signal = false;
    private String name;
    private List<String> fileNames = new ArrayList<>();

    public Execute_script() {
        name = "execute_script";
        Commands.addNewCommand("execute_script", this);
    }

    public static boolean getSignal() {
        return signal;
    }

    public static String getFileName() {
        return fileName;
    }

    @Override
    public void Do(String parameter1) throws IOException {
//        fileName = parameter1;
//        if (parameter1 == null) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Введите название файла");
//            System.out.print("$ ");
//            fileName = scanner.nextLine();
//            if (fileName.isEmpty()) {
//                System.out.println("Название файла не может быть null");
//            } else {
//                Commands commands = new Commands(this.name, fileName);
//            }
//        } else {
//            InputOutput inputOutput = new InputOutput();
//            if (fileNames.contains(fileName)) {
//                System.err.println("STACKOVERFLOW");
//            } else {
//                signal = true;
//                fileNames.add(fileName);
//                inputOutput.InputFile(fileName);
//                signal = false;
//            }
//        }
    }

    //TODO переделать, по сути просто с другого класса читать

    /**
     * get info about command
     *
     * @return String
     */
    public String info() {
        return name + ": считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }
}