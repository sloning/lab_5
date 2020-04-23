package client_controller;

import input_output.FabricOfMovies;
import input_output.InputOutput;
import movie.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validation {       // TODO сделать обработку неизвестных команд, а то все падает
    private String name = null;
    private String parameter = null;
    private Movie movie = null;
    private static String fileName;
    private static boolean signal = false;
    private List<String> fileNames = new ArrayList<>();

    public Validation(String name) throws IOException {
        this.name = name;
    }

    public void check() throws IOException {
        if ((name.equals("insert")) || (name.equals("update_id") )) {
            this.insertKeyCheck(parameter);
            this.insertValidation(parameter);
        } else
        if (name.equals("count_by_genre")) {
            this.countByGenreCheck(parameter);
        } else
        if (name.equals("execute_script")) {
            this.executeScriptCheck(parameter);
        } else
        if (name.equals("exit")) {
            System.exit(0);
        } else
        if (name.equals("filter_starts_with_name")) {
            this.filterStartsWithNameCheck(parameter);
        } else
        if (name.equals("remove_if_lowe")) {
            this.insertKeyCheck(parameter);
        } else
        if (name.equals("remove")) {
            this.removeKeyCheck(parameter);
        } else
        if (name.equals("replace_if_lowe")) {
            this.insertKeyCheck(parameter);
        } else if (name.equals("clear") || name.equals("help") || name.equals("min_by_id") || name.equals("show") || name.equals("history") || name.equals("info")) {

        } else {
            System.out.println("Вы ввели неверное название команды");
            name = null;
        }

    }

    public void insertKeyCheck(String parameter) {
        String key = parameter;
        if (parameter == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите ключ");
            System.out.print("$ ");
            key = scanner.nextLine();
            if (key.equals("") || key == null) {
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
            if (key.equals("") || key == null) {
                System.out.println("жанр не может быть null");
                this.countByGenreCheck(key);
            } else {
                this.parameter = key;
            }
        }
    }

    public void executeScriptCheck(String parameter) throws IOException {
        this.fileName = parameter;
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
            if (key.equals("") || key == null) {
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
            if (key.equals("") || key == null) {
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