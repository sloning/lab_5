package client.input_output;

import server.movie.Location;
import server.movie.Movie;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Creates new server.movie object
 *
 * @author Abay
 */
public class FabricOfMovies {
    private String Name;

    public void setNameForMovie(Movie movie) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название фильма");
            System.out.print("$ ");
            String name = scanner.nextLine();
            if (name.equals("") || name == null) throw new NullPointerException();
            this.Name = name;
            movie.setName(name);
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значения заново");
            this.setNameForMovie(movie);
        } catch (NullPointerException e) {
            System.out.println("Это поле не может быть null. Введите значение заново");
            this.setNameForMovie(movie);
        }
    }

    public void setCoordinatesForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите координаты");
            System.out.println("Введите координату x");
            System.out.print("$ ");
            Integer x = scanner.nextInt();
            if (x == null) throw new NullPointerException();
            System.out.println("Введите координату y");
            System.out.print("$ ");
            int y = scanner.nextInt();
            movie.setCoordinates(x, y);
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значения заново");
            this.setCoordinatesForMovie(movie);
        } catch (NullPointerException e) {
            System.out.println("Это поле не может быть null. Введите значение заново");
            this.setCoordinatesForMovie(movie);
        }
    }

    public void setOscarsCountForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите количество полученных фильмом оскаров");
            System.out.print("$ ");
            Integer oscars = scanner.nextInt();
            if (oscars == null) throw new NullPointerException();
            if (oscars <= 0) throw new NumberFormatException();
            movie.setOscarsCount(oscars);
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            this.setOscarsCountForMovie(movie);
        } catch (NumberFormatException e) {
            System.out.println("Значение этого поля не может быть отрицательным или равным нулю");
            this.setOscarsCountForMovie(movie);
        } catch (NullPointerException e) {
            System.out.println("Это поле не может быть null. Введите значение заново");
            this.setCoordinatesForMovie(movie);
        }
    }

    public void setLengthForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите длину фильма");
            System.out.print("$ ");
            int length = scanner.nextInt();
            if (length <= 0) throw new NumberFormatException();
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
        System.out.print("$ ");
        String genre = scanner.nextLine();
        if ((genre.equals("COMEDY")) || (genre.equals("MUSICAL")) || (genre.equals("FANTASY") || (genre.equals("")))) {
            movie.setGenre(genre);
        } else {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            this.setGenreForMovie(movie);
        }
    }

    public void setMpaaRatingForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите возрастной рейтинг: \nG (нет возрастных ограничений), \nPG (Рекомендуется присутствие родителей), \nPG_13 (Детям до 13 просмотр запрещен)");
        System.out.print("$ ");
        String rating = scanner.nextLine();
        if ((rating.equals("G") || (rating.equals("PG") || (rating.equals("PG_13") || (rating.equals("")))))) {
            movie.setMpaaRating(rating);
        } else {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            this.setMpaaRatingForMovie(movie);
        }
    }

    private String setDirectorName(Movie movie) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя");
            System.out.print("$ ");
            String nameDirector = scanner.nextLine();
            if (nameDirector.isEmpty()) throw new NullPointerException();
            return nameDirector;
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            return this.setDirectorName(movie);
        } catch (NullPointerException e) {
            System.out.println("Значение этого поля не может быть null. Введите значение заново");
            return this.setDirectorName(movie);
        }
    }

    private double setDirectorHeight(Movie movie) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите высоту");
            System.out.print("$ ");
            Double height = scanner.nextDouble();
            if (height <= 0) throw new NumberFormatException();
            return height;
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            return this.setDirectorHeight(movie);
        } catch (NumberFormatException e) {
            System.out.println("Значение этого поля не может быть отрицательным или равным нулю");
            return this.setDirectorHeight(movie);
        }
    }

    private float setDirectorWeight(Movie movie) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите вес");
            System.out.print("$ ");
            Float weight = scanner.nextFloat();
            if (weight <= 0) throw new NumberFormatException();
            return weight;
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            return this.setDirectorWeight(movie);
        } catch (NumberFormatException e) {
            System.out.println("Значение этого поля не может быть отрицательным или равным нулю");
            return this.setDirectorWeight(movie);
        }
    }

    private String setDirectorLocationName(Movie movie) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите геолокацию директора");
            System.out.println("Введите название геолокации");
            System.out.print("$ ");
            String nameLocation = scanner.nextLine();
            if (nameLocation.isEmpty()) throw new NullPointerException();
            return nameLocation;
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            return this.setDirectorLocationName(movie);
        } catch (NullPointerException e) {
            System.out.println("Значение этого поля не может быть null. Введите значение заново");
            return this.setDirectorLocationName(movie);
        }
    }

    private int setDirectorX(Movie movie) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите координату x");
            System.out.print("$ ");
            Integer xLocation = scanner.nextInt();
            if (xLocation == null) throw new NullPointerException();
            return xLocation;
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            return this.setDirectorX(movie);
        } catch (NullPointerException e) {
            System.out.println("Значение этого поля не может быть null. Введите значение заново");
            return this.setDirectorX(movie);
        }
    }

    private long setDirectorY(Movie movie) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите координату y");
            System.out.print("$ ");
            long yLocation = scanner.nextLong();
            return yLocation;
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            return this.setDirectorY(movie);
        }
    }

    public int setDirectorZ(Movie movie) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите координату z");
            System.out.print("$ ");
            Integer zLocation = scanner.nextInt();
            if (zLocation == null) throw new NullPointerException();
            return zLocation;
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели значение неверного типа. Введите значение заново");
            return this.setDirectorZ(movie);
        } catch (NullPointerException e) {
            System.out.println("Значение этого поля не может быть null. Введите значение заново");
            return this.setDirectorZ(movie);
        }
    }

    public void setDirectorForMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Укажите данные директора фильма");
            movie.setDirector(this.setDirectorName(movie), this.setDirectorHeight(movie), this.setDirectorWeight(movie), new Location(this.setDirectorLocationName(movie), this.setDirectorX(movie), this.setDirectorY(movie), this.setDirectorZ(movie)));
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
     * Reads input and therefore creates new server.movie
     *
     * @return new server.movie object
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
