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
            strLine = strLine.replace("\",\"", " ").replace("\"","").replace("x =", "x=").replace("z =", "z=").replace("y =", "y=").replace(",", "");
            String[] parameters;
            parameters=strLine.split(" ");
            movieCollection.newMovie(parameters[1], parameters[3], parameters[6], parameters[8], parameters[12], parameters[17], parameters[19], parameters[21], parameters[24],parameters[27],parameters[29], parameters[32], parameters[34],parameters[37], parameters[39], parameters[41]);
        }
    }
}
