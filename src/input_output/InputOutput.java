package input_output;

import controller.Controller;
import java.io.*;
import au.com.bytecode.opencsv.CSVWriter;

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
        File file = new File("Read.txt");
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

