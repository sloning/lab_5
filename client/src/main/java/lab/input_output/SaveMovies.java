package src.main.java.lab.input_output;

import src.main.java.data.MovieCollection;
import src.main.java.lab.movie.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SaveMovies {
    public void getOut() throws IOException {
        String fileName = "write1.csv";
        if (InputOutput.checkFile(fileName) == 0) {
            MovieCollection movieCollection = new MovieCollection();

            FileWriter writer = new FileWriter(fileName);

            for (Map.Entry<String, Movie> elementOfMap : movieCollection.getMap().entrySet()) {
                Movie movie = elementOfMap.getValue();
                String movieKey = elementOfMap.getKey();
                String movieInString = "\"";
                movieInString = movieInString + movie.getInfo() + "\nMovie Key: " + movieKey;
                movieInString = movieInString.replace("\n", "\",\"").replace("\r", "\",\"");
                movieInString = movieInString + "\"" + "\n";
                writer.write(movieInString);
            }
            writer.close();
        }
    }
}
