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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadMovies {
    public void load(String fileName) throws IOException, ParseException {
        MovieCollection movieCollection = new MovieCollection();

        FileInputStream fstream = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        Pattern moviePattern = Pattern.compile("Movie:\\s(\\w+\\s*\\w*\\s*\\w*)\"");    // Название фильма может состоять максимум из 3 слов(a-z, A-Z, 0-9)
        Pattern movieIdPattern = Pattern.compile("ID:\\s([-+]?\\d+)\"");
        Pattern datePattern = Pattern.compile("Date\\sof\\screation:\\s\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\"");
        Pattern amountOfOscarsPattern = Pattern.compile("Count of oscars:\\s\\d+\"");
        Pattern movieLengthPattern = Pattern.compile("Length:\\s\\d+\"");
        Pattern directorNamePattern = Pattern.compile("Director:\\sName:\\s\\w+\\w*\"");
        Pattern directorHeightPattern = Pattern.compile("Height:\\s\\d+.\\d+\"");
        Pattern directorWeightPattern = Pattern.compile("Weight:\\s\\d+.\\d+\"");
        Pattern directorDatePattern = Pattern.compile("Birthday:\\s\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\"");
        Pattern coordinatesPattern = Pattern.compile("Coordinates:\\sx\\s=\\s\\d+,\\sy\\s=\\s\\d+.\\d+");
        Pattern locationPattern = Pattern.compile("Location:\\sx\\s=\\s\\d+\\sy\\s=\\s\\d+\\sz\\s=\\s\\d+\"");
        Pattern genrePattern = Pattern.compile("Genre:\\s\\w+\"");
        Pattern ratingPattern = Pattern.compile("MPAA rating:\\s\\w+\"");
        Pattern cX = Pattern.compile("x\\s=\\s\\d+");
        Pattern coordY = Pattern.compile("y\\s=\\s\\d+.\\d+");
        Pattern cY = Pattern.compile("y\\s=\\s\\d+");
        Pattern cZ = Pattern.compile("z\\s=\\s\\d+");
        Pattern movieKeyPattern = Pattern.compile("Movie\\sKey:\\s\\d+\"");

        while ((strLine = br.readLine()) != null) {
            try {
                Matcher movieMatcher = moviePattern.matcher(strLine);
                Matcher movieIdMatcher = movieIdPattern.matcher(strLine);
                Matcher dateMatcher = datePattern.matcher(strLine);
                Matcher amountOfOscarsMatcher = amountOfOscarsPattern.matcher(strLine);
                Matcher movieLengthMatcher = movieLengthPattern.matcher(strLine);
                Matcher directorNameMatcher = directorNamePattern.matcher(strLine);
                Matcher directorHeightMatcher = directorHeightPattern.matcher(strLine);
                Matcher directorWeightMatcher = directorWeightPattern.matcher(strLine);
                Matcher directorDateMatcher = directorDatePattern.matcher(strLine);
                Matcher coordinatesMatcher = coordinatesPattern.matcher(strLine);
                Matcher locationMatcher = locationPattern.matcher(strLine);
                Matcher genreMatcher = genrePattern.matcher(strLine);
                Matcher ratingMatcher = ratingPattern.matcher(strLine);
                Matcher movieKeyMatcher = movieKeyPattern.matcher(strLine);
                movieMatcher.find();
                movieIdMatcher.find();
                dateMatcher.find();
                amountOfOscarsMatcher.find();
                movieLengthMatcher.find();
                directorNameMatcher.find();
                directorHeightMatcher.find();
                directorWeightMatcher.find();
                directorDateMatcher.find();
                coordinatesMatcher.find();
                locationMatcher.find();
                genreMatcher.find();
                ratingMatcher.find();
                movieKeyMatcher.find();

                String coordinates = strLine.substring(coordinatesMatcher.start(), coordinatesMatcher.end());
                String location = strLine.substring(locationMatcher.start(), locationMatcher.end() - 1);
                Matcher coordXMatcher = cX.matcher(coordinates);
                Matcher coordYMatcher = coordY.matcher(coordinates);
                Matcher cXMatcher = cX.matcher(location);
                Matcher cYMatcher = cY.matcher(location);
                Matcher cZMatcher = cZ.matcher(location);
                coordXMatcher.find();
                coordYMatcher.find();
                cXMatcher.find();
                cYMatcher.find();
                cZMatcher.find();

                String movieName = strLine.substring(movieMatcher.start() + 7, movieMatcher.end() - 1);
                String movieId = strLine.substring(movieIdMatcher.start() + 4, movieIdMatcher.end() - 1);
                String directorName = strLine.substring(directorNameMatcher.start() + 16, directorNameMatcher.end() - 1);
                String movieRating = strLine.substring(ratingMatcher.start() + 13, ratingMatcher.end() - 1);
                String movieGenre = strLine.substring(genreMatcher.start() + 7, genreMatcher.end() - 1);
                int amountOfOscars = Integer.parseInt(strLine.substring(amountOfOscarsMatcher.start() + 17, amountOfOscarsMatcher.end() - 1));
                int x = Integer.parseInt(coordinates.substring(coordXMatcher.start() + 4, coordXMatcher.end()));
                int movieLength = Integer.parseInt(strLine.substring(movieLengthMatcher.start() + 8, movieLengthMatcher.end() - 1));
                int locX = Integer.parseInt(location.substring(cXMatcher.start() + 4, cXMatcher.end()));
                int locY = Integer.parseInt(location.substring(cYMatcher.start() + 4, cYMatcher.end()));
                int locZ = Integer.parseInt(location.substring(cZMatcher.start() + 4, cZMatcher.end()));
                float y = Float.parseFloat(coordinates.substring(coordYMatcher.start() + 4, coordYMatcher.end()));
                float directorWeight = Float.parseFloat(strLine.substring(directorWeightMatcher.start() + 8, directorWeightMatcher.end() - 1));
                double directorHeight = Double.parseDouble(strLine.substring(directorHeightMatcher.start() + 8, directorHeightMatcher.end() - 1));
                Date date = FormattingDate(strLine.substring(dateMatcher.start() + 18, dateMatcher.end() - 1));
                Date directorDate = FormattingDate(strLine.substring(directorDateMatcher.start() + 10, directorDateMatcher.end() - 1));
                String movieKey = strLine.substring(movieKeyMatcher.start() + 11, movieKeyMatcher.end() - 1);

                Coordinates movieCoordinates = new Coordinates(x, y);
                Location movieLocation = new Location(locX, locY, locZ);
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

    public static Date FormattingDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = formatter.parse(str);
        return date;
    }
}
