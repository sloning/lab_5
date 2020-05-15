package client_controller;

import input_output.FabricOfMovies;
import input_output.InputOutput;
import movie.Location;
import movie.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validation {
    private String name;
    private String parameter = null;
    private Movie movie = null;
    private static String fileName;
    private static boolean signal = false;      // Если true, то выполняется сценарий с отправкой скрипта
    public static boolean sendReady = true;
    private final List<String> fileNames = new ArrayList<>();

    public Validation(String name) {
        this.name = name;
    }

    public void check() throws IOException {
        sendReady = true;
        switch (name) {
            case "insert":
            case "update_id":
                this.insertKeyCheck(parameter);
                this.insertValidation(parameter);
                break;
            case "count_by_genre":
                this.countByGenreCheck(parameter);
                break;
            case "execute_script":
                this.executeScriptCheck(parameter);
                break;
            case "exit":
                System.exit(0);
            case "filter_starts_with_name":
                this.filterStartsWithNameCheck(parameter);
                break;
            case "remove_if_lowe":
            case "replace_if_lowe":
                this.insertKeyCheck(parameter);
                break;
            case "remove":
                this.removeKeyCheck(parameter);
                break;
            case "clear":
            case "help":
            case "min_by_id":
            case "show":
            case "history":
            case "info":
                break;
            default:
                System.out.println("Вы ввели неверное название команды");
                //System.out.println("Но мы все равно отправим ее на сервер");
                sendReady = false;
                name = null;
                break;
        }
    }

    public void insertKeyCheck(String parameter) {
        String key = parameter;
        if (key == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите ключ");
            System.out.print("$ ");
            key = scanner.nextLine();
            if (key.equals("")) {
                System.out.println("Ключ не может быть null");
                this.insertKeyCheck(key);
            } else {
                this.parameter = key;
            }
        }
    }

    public void countByGenreCheck(String parameter) {
        if (parameter == null) {
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите жанр");
            System.out.print("$");
            key = scanner.nextLine();
            if (key.equals("")) {
                System.out.println("жанр не может быть null");
                this.countByGenreCheck(key);
            } else {
                this.parameter = key;
            }
        }
    }

    public void executeScriptCheck(String parameter) throws IOException {
        fileName = parameter;
        if (fileName == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название файла");
            System.out.print("$ ");
            fileName = scanner.nextLine();
            if (fileName.isEmpty()) {
                System.out.println("Название файла не может быть null");
                this.executeScriptCheck(fileName);
            } else {
                this.parameter = fileName;
                executeScriptCheck(fileName);
            }
        } else {
            InputOutput inputOutput = new InputOutput();
            if (fileNames.contains(fileName)) {
                System.err.println("STACKOVERFLOW");
            } else {
                signal = true;
                fileNames.add(fileName);
                inputOutput.InputFile(fileName);
            }
        }
    }

    public void filterStartsWithNameCheck(String parameter) {
        if (parameter == null) {
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите подстроку");
            System.out.print("$");
            key = scanner.nextLine();
            if (key.equals("")) {
                System.out.println("подстрока не может быть null");
                this.filterStartsWithNameCheck(key);
            } else {
                this.parameter = key;
            }
        }
    }

    public void removeKeyCheck(String parameter) {
        if (parameter == null) {
            Scanner scanner = new Scanner(System.in);
            String key;
            System.out.println("Введите ключ объекта, который хотите удалить");
            System.out.print("$");
            key = scanner.nextLine();
            if (key.equals("")) {
                System.out.println("Ключ не может быть null");
                this.removeKeyCheck(key);
            } else {
                this.parameter = key;
            }
        }
    }

    public void insertValidation(String parameter) throws IOException {
        if (signal) {
            int c = InputOutput.count;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (c-- > 0) bufferedReader.readLine();
            movie.setName(bufferedReader.readLine());
            int cordX = Integer.parseInt(bufferedReader.readLine());
            String cordYString = bufferedReader.readLine();
            if (cordYString.isEmpty()) movie.setCoordinates(cordX);
            else movie.setCoordinates(cordX, Float.parseFloat(cordYString));
            movie.setOscarsCount(Integer.parseInt(bufferedReader.readLine()));
            movie.setLength(Integer.parseInt(bufferedReader.readLine()));
            movie.setGenre(bufferedReader.readLine());
            movie.setMpaaRating(bufferedReader.readLine());
            String dirName = bufferedReader.readLine();
            double dirHeight = Double.parseDouble(bufferedReader.readLine());
            float dirWeight = Float.parseFloat(bufferedReader.readLine());
            String locName = bufferedReader.readLine();
            int locX = Integer.parseInt(bufferedReader.readLine());
            String locYString = bufferedReader.readLine();
            int locZ = Integer.parseInt(bufferedReader.readLine());
            if (locYString.isEmpty())
                movie.setDirector(dirName, dirHeight, dirWeight, new Location(locName, locX, locZ));
            else
                movie.setDirector(dirName, dirHeight, dirWeight, new Location(locName, locX, Long.parseLong(locYString), locZ));
        } else {
            FabricOfMovies fabricOfMovies = new FabricOfMovies();
            movie = fabricOfMovies.create();
        }
    }

    public String getName(){
        return name;
    }

    public String getParameter(){
        return parameter;
    }

    public Movie getMovie(){
        return movie;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public static boolean getSignal(){
        return signal;
    }

    public static void setSignal(boolean c){
        signal = c;
    }
}