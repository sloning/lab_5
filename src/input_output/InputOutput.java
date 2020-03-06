package input_output;

import controller.Controller;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Read input from console and write to a file
 *
 * @author Abay
 */
public class InputOutput {
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
            Controller controller = new Controller(command);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Вы ввели неверное название команды (введите help, чтобы получить справку по доступным командам)");
        }
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
            if (file.isDirectory()) {
                System.out.println("Необходим обязательный аргумент: Полное имя файла данных, не директория");
            }
            if (!file.exists()) {
                System.out.println("Файл не найден");
            }
            if (!file.canRead()) {
                System.out.println("Ошибка доступа на чтение");
            }
            if (!file.canWrite()) {
                System.out.println("Ошибка доступа на запись");
            }
            FileReader fileReader = new FileReader(file);
            String s;

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                s = bufferedReader.readLine().replaceAll("\\s+", " ");
                count++;
                Controller controller = new Controller(s);
                if (Pattern.matches("insert\\s[A-Za-z0-9_]+", s)) {
                    for (int i = 0; i < 14; i++) {
                        bufferedReader.readLine();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Вы ввели неверное название файла");
        }
    }
}