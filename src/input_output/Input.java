package input_output;

import java.util.Scanner;

public class Input {
    public String Input(){
        System.out.println("Введите команду");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
