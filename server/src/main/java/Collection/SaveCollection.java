package Collection;

import movie.Movie;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static data.FileCheck.checkFile;

public class SaveCollection {
    private Thread backgroundReaderThread = null;

    public void save() throws IOException {
        String fileName = "write1.csv";
        if (checkFile(fileName) == 0) {
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
            System.out.printf("Успех\n");
            writer.close();
        }
    }

    public void checkForSaveCommand() throws IOException {
        backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("save")) save();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}
