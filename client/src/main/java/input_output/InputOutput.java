package input_output;

import client_controller.Controller;
import data.Shell;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static data.FileCheck.checkFile;

/**
 * Read input from console and write to a file
 *
 * @author Abay
 */
public class InputOutput {
    Controller controller = new Controller();

    public static int count = 0;

    /**
     * Read inputs from console
     *
     * @return input
     * @throws IOException
     */
    public void Input() throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите команду");
            System.out.print("$ ");
            String command = null;
            try {
                command = sc.nextLine();
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
            if (!controller.commandController(command)) Input();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Вы ввели неверное название команды (введите help, чтобы получить справку по доступным командам)");
        }
    }

    public String InputOneCommand(String command) throws IOException {
        try {
            boolean f = controller.commandController(command);
            return controller.getHistory();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Вы ввели неверное название команды (введите help, чтобы получить справку по доступным командам)");
        }
        return null;
    }

    public void setUser(String user) {
        this.controller.setUser(user);
    }

    public void setPassword(String password) {
        this.controller.setPassword(password);
    }

    public Shell getShell(){
        return controller.getShell();
    }


    /**
     * Read inputs from file
     *
     * @return input
     * @throws IOException
     */
    public void InputFile(String nameFile) throws IOException {
        try {
            File file = new File(nameFile);
            if (checkFile(nameFile).equals("0")) {
                FileReader fileReader = new FileReader(file);
                String str;
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()) {
                    str = bufferedReader.readLine().replaceAll("\\s+", " ");
                    if (str.isEmpty()) continue;
                    count++;
                    controller.commandController(str);
                    CollectionOfShells sheelCollection = new CollectionOfShells();
                    sheelCollection.addShell(controller.getShell());
                    if (Pattern.matches("insert\\s[A-Za-z0-9_]+", str)) {
                        for (int i = 0; i < 14; i++) {
                            bufferedReader.readLine();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Вы ввели неверное название файла");
        }
    }
}