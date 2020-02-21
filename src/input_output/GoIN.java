package input_output;

import data.MovieCollection;
import movie.Coordinates;
import movie.Location;
import movie.Movie;
import movie.Person;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoIN {
    public static Date FormattingDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = formatter.parse(str);
        return date;
    }

    public void in(String file) throws IOException, ParseException {
        MovieCollection movieCollection = new MovieCollection();
        FileInputStream fstream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        while ((strLine = br.readLine()) != null) {
            String movieName = strLine.substring(8, strLine.indexOf("\"", 8));
            int begin = strLine.indexOf("\"", 8) + 7;
            String movieKey = (strLine.substring(begin, strLine.indexOf("\"", begin)));

            begin = strLine.indexOf("\"", begin) + 20;
            int x = Integer.parseInt(strLine.substring(begin, strLine.indexOf(",", begin)));
            begin = strLine.indexOf(",", begin) + 6;
            float y = Float.parseFloat(strLine.substring(begin, strLine.indexOf("\"", begin)));
            Coordinates coordinates = new Coordinates(x, y);

            begin = strLine.indexOf(",", begin) + 20;
            Date date = FormattingDate(strLine.substring(begin, strLine.indexOf("\"", begin)));

            begin = 20 + strLine.indexOf("\"", begin);
            int amountOfOscars = Integer.parseInt(strLine.substring(begin, strLine.indexOf("\"", begin)));

            begin = 11 + strLine.indexOf("\"", begin);
            int movieLength = Integer.parseInt(strLine.substring(begin, strLine.indexOf("\"", begin)));

            begin = 10 + strLine.indexOf("\"", begin);
            String movieGenre = strLine.substring(begin, strLine.indexOf("\"", begin));

            begin = 16 + strLine.indexOf("\"", begin);
            String movieRating = strLine.substring(begin, strLine.indexOf("\"", begin));

            begin = 19 + strLine.indexOf("\"", begin);
            String directorName = strLine.substring(begin, strLine.indexOf("\"", begin));

            begin = 13 + strLine.indexOf("\"", begin);
            Date directorDate = FormattingDate(strLine.substring(begin, strLine.indexOf("\"", begin)));

            begin = 11 + strLine.indexOf("\"", begin);
            double directorHeight = Double.parseDouble(strLine.substring(begin, strLine.indexOf("\"", begin)));

            begin = 11 + strLine.indexOf("\"", begin);
            float directorWeight = Float.parseFloat(strLine.substring(begin, strLine.indexOf("\"", begin)));

            begin = 17 + strLine.indexOf("\"", begin);
            int locX = Integer.parseInt(strLine.substring(begin, strLine.indexOf(" ", begin)));

            begin = 5 + strLine.indexOf(" ", begin);
            int locY = Integer.parseInt(strLine.substring(begin, strLine.indexOf(" ", begin)));

            begin = 5 + strLine.indexOf(" ", begin);
            int locZ = Integer.parseInt(strLine.substring(begin, strLine.indexOf("\"", begin)));

            Location location = new Location(locX, locY, locZ);
            Person director = new Person(directorName, directorHeight, directorWeight, location, directorDate);

            Movie movie = new Movie(movieName, date, coordinates, amountOfOscars, movieLength, director);
            movie.setGenre(movieGenre);
            movie.setMpaaRating(movieRating);

            movieCollection.putMovie(movieKey, movie);

//            System.out.println("------------------------------------");
//            System.out.println(movieID);
//            System.out.println(movieName);
//            System.out.println(x);
//            System.out.println(y);
//            System.out.println(date);
//            System.out.println(amountOfOscars);
//            System.out.println(movieLength);
//            System.out.println(movieGenre);
//            System.out.println(movieRating);
//            System.out.println(directorName);
//            System.out.println(directorDate);
//            System.out.println(directorHeight);
//            System.out.println(directorWeight);
//            System.out.println(locX);
//            System.out.println(locY);
//            System.out.println(locZ);
        }
    }
}
