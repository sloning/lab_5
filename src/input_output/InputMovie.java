package input_output;

import movie.Location;
import movie.Movie;

import java.util.Scanner;

public class InputMovie { //TODO переименовать в фабртику
    public static Movie create(Long key, String movieName) {
        Movie movie = new Movie(key, movieName);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите координаты");
        System.out.println("Введите координату x");
        int x = scanner.nextInt();
        System.out.println("Введите координату y");
        int y = scanner.nextInt();
        movie.setCoordinates(x,y);
        System.out.println("Введите количество полученных фильмом оскаров");
        int oscars = scanner.nextInt();
        movie.setOscarsCount(oscars);
        System.out.println("Введите длину фильма");
        int length = scanner.nextInt();
        movie.setLength(length);
        System.out.println("Выберите жанр: \nCOMEDY, \nMUSICAL, \nFANTASY \n(Или напишите пропустить)");
        String genre = scanner.nextLine();
        movie.setGenre(genre);
        System.out.println("Укажите возрастной рейтинг: \nG (нет возрастных ограничений), \nPG (Рекомендуется присутствие родителей), \nPG_13 (Детям до 13 просмотр запрещен) \n(Или напишите пропутить)");
        String rating = scanner.nextLine();
        movie.setMpaaRating(rating);
        System.out.println("Укажите данные директора фильма");
        System.out.println("Введите имя");
        String nameDirector = scanner.nextLine();
        System.out.println("Введите высоту (или напишите 0, если хотите пропустить)");
        Double height = scanner.nextDouble();
        System.out.println("Введите вес (или напишите 0, если хотите пропустить)");
        Float weight = scanner.nextFloat();
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
        return movie;
    }
}
