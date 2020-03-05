package input_output;

import controller.Controller;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
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
            if (checkStackOverFlow(nameFile) == 0) {
                File file = new File(nameFile);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()) {
                    Controller controller = new Controller(bufferedReader.readLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Вы ввели неверное название файла");
        }
    }

    public int checkStackOverFlow(String nameFile) throws IOException {
        File file = new File(nameFile);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String fileValue = new String(data, "UTF-8");
        Pattern errorPattern = Pattern.compile("execute_script\\s" + nameFile);
        Matcher errorMatcher = errorPattern.matcher(fileValue);
        if (errorMatcher.find()) {
            System.out.println("Вы чего творите то? Не по-христиански это все (StackOverFlow tuta)");
            return 1;
        }
        return 0;
    }
}