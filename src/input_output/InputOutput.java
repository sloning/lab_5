package input_output;

import controller.Controller;

import java.io.*;
import java.util.Scanner;

public class InputOutput { //TODO объеденить, вынести инпут в метод
    public void Input() throws Exception{
        File file = new File("Read.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()){
            Controller controller = new Controller(bufferedReader.readLine());
        }

        //System.out.println("Введите команду");
        //Scanner sc = new Scanner(System.in);
        //Controller controller = new Controller(sc.nextLine());
    }
}
