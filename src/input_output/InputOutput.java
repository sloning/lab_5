package input_output;

import controller.Controller;
import java.io.*;
import au.com.bytecode.opencsv.CSVWriter;


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
        String csv = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        //Create record
        String [] record = "4,David,Miller,Australia,30".split(",");
        //Write the record to file
        writer.writeNext(record);
        //close the writer
        writer.close();
    }
}

