package input_output;

import controller.Controller;

import java.util.Scanner;

public class Input {
    public Input(){
        System.out.println("Введите команду");
        Scanner sc = new Scanner(System.in);
        Controller controller = new Controller(sc.nextLine());
    }
}
