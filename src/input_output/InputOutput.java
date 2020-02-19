package input_output;

import controller.Controller;
import java.io.*;

public class InputOutput { //TODO объеденить, вынести инпут в метод
    public void Input() throws Exception {
        File file = new File("Read.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            Controller controller = new Controller(bufferedReader.readLine());
        }
    }

    public void Output(String text) throws IOException {
        File file = new File("Write.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}

