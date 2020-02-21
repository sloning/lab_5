package input_output;

import data.MovieCollection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoIN {
    public void in() throws IOException {
        MovieCollection movieCollection = new MovieCollection();
        FileInputStream fstream = new FileInputStream("write1.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        while ((strLine = br.readLine()) != null){
            //strLine = strLine.replace
        }
    }
}
