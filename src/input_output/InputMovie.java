package input_output;

import movie.Location;
import movie.Movie;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Creates new movie object
 * @author Abay
 */
public class InputMovie { //TODO переименовать в фабртику
    private String Name;

    public void setNameForMovie(Movie movie){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название фильма");
            String name = scanner.nextLine();
            this.Name = name;
            movie.setName(name);
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значения заново");
            this.setNameForMovie(movie);
        }
    }

    public void setCoordinatesForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите координаты");
            System.out.println("Введите координату x");
            int x = scanner.nextInt();
            System.out.println("Введите координату y");
            int y = scanner.nextInt();
            movie.setCoordinates(x, y);
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значения заново");
            this.setCoordinatesForMovie(movie);
        }
    }

    public void setOscarsCountForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите количество полученных фильмом оскаров");
            int oscars = scanner.nextInt();
            if (oscars<=0) throw new NumberFormatException();
            movie.setOscarsCount(oscars);
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            this.setOscarsCountForMovie(movie);
        } catch (NumberFormatException e) {
            System.out.println("Значение этого поля не может быть отрицательным или равным нулю");
            this.setOscarsCountForMovie(movie);
        }
    }

    public void setLengthForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите длину фильма");
            int length = scanner.nextInt();
            if (length<=0) throw new NumberFormatException();
            movie.setLength(length);
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            this.setLengthForMovie(movie);
        } catch (NumberFormatException e) {
            System.out.println("Значение этого поля не может быть отрицательным или равным нулю");
            this.setLengthForMovie(movie);
        }
    }

    public void setGenreForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите жанр: \nCOMEDY, \nMUSICAL, \nFANTASY");
        String genre = scanner.nextLine();
        if ((genre.equals("COMEDY")) || (genre.equals("MUSICAL")) || (genre.equals("FANTASY"))) {
            movie.setGenre(genre);
        } else {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            this.setGenreForMovie(movie);
        }
    }

    public void setMpaaRatingForMovie(Movie movie){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите возрастной рейтинг: \nG (нет возрастных ограничений), \nPG (Рекомендуется присутствие родителей), \nPG_13 (Детям до 13 просмотр запрещен)");
        String rating = scanner.nextLine();
        if ((rating.equals("G") || (rating.equals("PG") || (rating.equals("PG_13"))))) {
            movie.setMpaaRating(rating);
        } else {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            this.setMpaaRatingForMovie(movie);
        }
    }

    public void setDirectorForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Укажите данные директора фильма");
            System.out.println("Введите имя");
            String nameDirector = scanner.nextLine();
            System.out.println("Введите высоту");
            Double height = scanner.nextDouble();
            if (height <= 0) throw new NumberFormatException();
            System.out.println("Введите вес");
            Float weight = scanner.nextFloat();
            if (weight <= 0) throw new NumberFormatException();

            System.out.println("Введите геолокацию директора");
            System.out.println("Введите название геолокации");
            String nameLocation = scanner.nextLine();
            System.out.println("Введите координату x");
            int xLocation = scanner.nextInt();
            System.out.println("Введите координату y");
            long yLocation = scanner.nextLong();
            System.out.println("Введите координату z");
            int zLocation = scanner.nextInt();
            movie.setDirector(nameDirector, height, weight, new Location(nameLocation, xLocation, yLocation, zLocation));
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            this.setDirectorForMovie(movie);
        } catch (NumberFormatException e) {
            System.out.println("Значение этого поля не может быть отрицательным или равным нулю");
            this.setDirectorForMovie(movie);
        }
    }

    public String getName() {
        return Name;
    }

    /**
     * Reads input and therefore creates new movie
     * @return new movie object
     */
    public Movie create() {
        Movie movie = new Movie();
        this.setNameForMovie(movie);
        this.setCoordinatesForMovie(movie);
        this.setOscarsCountForMovie(movie);
        this.setLengthForMovie(movie);
        this.setGenreForMovie(movie);
        this.setMpaaRatingForMovie(movie);
        this.setDirectorForMovie(movie);

        return movie;
    }
}