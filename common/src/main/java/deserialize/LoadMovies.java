package deserialize;

import movie.Coordinates;
import movie.Location;
import movie.Movie;
import movie.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoadMovies {
    public static Date FormattingDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.parse(str);
    }

    public int countMovies(String movies) {
        Pattern pattern = Pattern.compile("Conec1cc");
        Matcher matcher = pattern.matcher(movies);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }

    public List<String> stringCutter(String movies, int amountOfMovies) {
        List<String> moviesList = new ArrayList<>();
        for (int i = 0; i < amountOfMovies; i++) {
            String subString = movies.substring(0, movies.indexOf("Conec1cc") + 8);
            moviesList.add(subString);
            movies = movies.replace(subString, "");
        }
        return moviesList;
    }

    public List<Movie> load(String movies) throws ParseException {
        int amountOfMovies = countMovies(movies);
        List<Movie> listOfMovies = new ArrayList<>();
        movies = movies.replace("\n", "\",\"").replace("\r", "\",\"");
        movies = movies + "\n";
        movies = "\"" + movies;
        List<String> moviesList = stringCutter(movies, amountOfMovies);

        String regex = "\"Movie:\\s(\\w+\\s*\\w*\\s*\\w*)\",\"ID:\\s([-+]?\\d+)\",\"Coordinates:\\sx\\s=\\s(\\d+),\\sy\\s=\\s(\\d+.\\d+)\",\"Date\\sof\\screation:\\s(\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\",\"Count of oscars:\\s(\\d+)\",\"Length:\\s(\\d+)\",\"Genre:\\s(\\w+)\",\"MPAA rating:\\s(\\w+)\",\"Director:\\sName:\\s(\\w+\\w*)\",\"Birthday:\\s(\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\",\"Height:\\s(\\d+.\\d+)\",\"Weight:\\s(\\d+.\\d+)\",\"Location\\sname:\\s(\\w+\\s*\\w*)\",\"Location:\\sx\\s=\\s(\\d+)\\sy\\s=\\s(\\d+)\\sz\\s=\\s(\\d+)\",\"User:\\s(\\w+\\s*\\w*\\s*\\w*)\",\"Movie\\sKey:\\s([a-zA-Z0-9]+)\"";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < amountOfMovies; i++) {
            try {
                Matcher matcher = pattern.matcher(moviesList.get(i));
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
                String movieKey = matcher.group(19);
                String user = matcher.group(18);
                String locName = matcher.group(14);

                Coordinates movieCoordinates = new Coordinates(x, y);
                Location movieLocation = new Location(locName, locX, locY, locZ);
                Person director = new Person(directorName, directorHeight, directorWeight, movieLocation, directorDate);
                Movie movie = new Movie(movieName, movieId, date, movieCoordinates, amountOfOscars, movieLength, director);
                movie.setGenre(movieGenre);
                movie.setMpaaRating(movieRating);
                movie.setMovieKey(movieKey);
                movie.setUser(user);
                listOfMovies.add(movie);
            } catch (java.lang.IllegalStateException e) {
                System.out.println("Error occurred while parsing movies");
                e.printStackTrace();
            }
        }
        return listOfMovies;
    }
}
