package input_output;

import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public void Output(String fileName, String command) throws IOException {
        try(FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(command);
//            writer.append('\n');
            writer.close();      //TODO Норм ли закрывать файл после каждой записи???
        }
    }
}
