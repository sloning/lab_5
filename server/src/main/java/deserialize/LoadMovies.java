package deserialize;

import Collection.MovieCollection;
import movie.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static data.FileCheck.checkFile;

public class LoadMovies {
    public static Date FormattingDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = formatter.parse(str);
        return date;
    }

    public void load(String fileName) throws IOException, ParseException {
        MovieCollection movieCollection = new MovieCollection();

        if (checkFile(fileName) == 0) {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String csvLine;

            String regex = "\"Movie:\\s(\\w+\\s*\\w*\\s*\\w*)\",\"ID:\\s([-+]?\\d+)\",\"Coordinates:\\sx\\s=\\s(\\d+),\\sy\\s=\\s(\\d+.\\d+)\",\"Date\\sof\\screation:\\s(\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\",\"Count of oscars:\\s(\\d+)\",\"Length:\\s(\\d+)\",\"Genre:\\s(\\w+)\",\"MPAA rating:\\s(\\w+)\",\"Director:\\sName:\\s(\\w+\\w*)\",\"Birthday:\\s(\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\",\"Height:\\s(\\d+.\\d+)\",\"Weight:\\s(\\d+.\\d+)\",\"Location\\sname:\\s(\\w+\\s*\\w*)\",\"Location:\\sx\\s=\\s(\\d+)\\sy\\s=\\s(\\d+)\\sz\\s=\\s(\\d+)\",\"Movie\\sKey:\\s([a-zA-Z0-9]+)\"";
            Pattern pattern = Pattern.compile(regex);
            while ((csvLine = br.readLine()) != null) {
                try {
                    Matcher matcher = pattern.matcher(csvLine);
                    matcher.find();

                    String movieName = matcher.group(1);
                    String movieId = matcher.group(2);
                    String directorName = matcher.group(10);
                    String movieRating = matcher.group(9);
                    String movieGenre = matcher.group(8);
                    int amountOfOscars = Integer.parseInt(matcher.group(6));
                    int x = Integer.parseInt(matcher.group(3));
                    int movieLength = Integer.parseInt(matcher.group(7));
                    int locX = Integer.parseInt(matcher.group(15));
                    int locY = Integer.parseInt(matcher.group(16));
                    int locZ = Integer.parseInt(matcher.group(17));
                    float y = Float.parseFloat(matcher.group(4));
                    float directorWeight = Float.parseFloat(matcher.group(13));
                    double directorHeight = Double.parseDouble(matcher.group(12));
                    Date date = FormattingDate(matcher.group(5));
                    Date directorDate = FormattingDate(matcher.group(11));
                    String movieKey = matcher.group(18);
                    String locName = matcher.group(14);

                    Coordinates movieCoordinates = new Coordinates(x, y);
                    Location movieLocation = new Location(locName, locX, locY, locZ);
                    Person director = new Person(directorName, directorHeight, directorWeight, movieLocation, directorDate);
                    Movie movie = new Movie(movieName, movieId, date, movieCoordinates, amountOfOscars, movieLength, director);
                    movie.setGenre(movieGenre);
                    movie.setMpaaRating(movieRating);
                    movieCollection.putMovie(movieKey, movie);
                } catch (java.lang.IllegalStateException e) {
                    System.out.println("Error occurred while loading movies");
                }
            }
        }
    }
}
