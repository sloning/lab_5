package input_output;

import data.MovieCollection;
import movie.Movie;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class SaveMovies {
    public void getOut() throws IOException {
        MovieCollection movieCollection = new MovieCollection();

        String fileName = "write1.csv";
        FileWriter writer = new FileWriter(fileName);

        Iterator it = movieCollection.getMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Movie> elementOfMap = (Map.Entry<String, Movie>) it.next();
            Movie movie = elementOfMap.getValue();
            String movieKey = elementOfMap.getKey();
            String movieInString = "\"";
            movieInString = movieInString + movie.getInfo() + "\nMovie Key: " + movieKey;
            movieInString = movieInString.replace("\n", "\",\"").replace("\r","\",\"");
            movieInString = movieInString + "\"" + "\n";
            writer.write(movieInString);
        }
        writer.close();
    }
}
