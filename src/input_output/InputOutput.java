package input_output;

import com.opencsv.CSVWriter;
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
     *
     * @return input
     * @throws IOException
     */
    public void Input() throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите команду");
            String command = sc.nextLine();
            Controller controller = new Controller(command);
        } catch (NullPointerException e) {
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
        File file = new File(nameFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            Controller controller = new Controller(bufferedReader.readLine());
        }
    }

    /**
     * Write something in file
     * @param text value to write in file
     * @throws IOException
     */
      public void Output(String text) throws IOException {
          /**
           * File name
           */
          String csv = "write.csw";
          CSVWriter writer = new CSVWriter(new FileWriter(csv));
          String[] record = text.split(",");
          writer.writeNext(record);
          writer.close();
      }
}