package input_output;

import controller.Controller;

import java.io.*;
import java.util.Scanner;

/**
 * Read input from console and write to a file
 * @author Abay
 */
public class InputOutput {
    /**
     * Read inputs from console
     * @return input
     * @throws IOException
     */
    public void Input() throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите команду");
            System.out.print("$ ");
            String command = sc.nextLine();
            Controller controller = new Controller(command);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Вы ввели неверное название команды (введите help, чтобы получить справку по доступным командам)");
        }
    }

    /**
     * Read inputs from file
     * @return input
     * @throws IOException
     */
    public void InputFile(String nameFile) throws IOException {
        try {
            File file = new File(nameFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                Controller controller = new Controller(bufferedReader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Вы ввели неверное название файла");
        }
    }
}