package input_output;

import client_controller.*;
import data.Shell;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

import static data.FileCheck.checkFile;

/**
 * Read input from console and write to a file
 *
 * @author Abay
 */
public class InputOutput {
    Controller controller = null;

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
            String command = sc.nextLine();
            controller = new Controller(command);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Вы ввели неверное название команды (введите help, чтобы получить справку по доступным командам)");
        }
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
            if (checkFile(nameFile) == 0) {
                FileReader fileReader = new FileReader(file);
                String str;

                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()) {
                    str = bufferedReader.readLine().replaceAll("\\s+", " ");
                    if (str.isEmpty()) continue;
                    count++;
                    Controller controller = new Controller(str);
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