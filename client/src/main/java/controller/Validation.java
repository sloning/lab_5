package controller;

import command_history.CommandHistory;
import input_output.FabricOfMovies;
import movie.Movie;

import java.util.Scanner;

public class Validation {
    private String name = null;
    private String parameter = null;
    private Movie movie = null;

    public Validation(String name, String parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    public void check() {
        if ((name.equals("insert")) || (name.equals("update_id") )) {
            FabricOfMovies fabricOfMovies = new FabricOfMovies();
            movie = fabricOfMovies.create();
            this.insertKeyCheck(parameter);
        }
        if (name.equals("count_by_genre")) {
            this.countByGenreCheck(parameter);
        }
        if (name.equals("execute_script")) {
            this.executeScriptCheck(parameter);
        }
        if (name.equals("exit")) {
            System.exit(0);
        }
        if (name.equals("filter_starts_with_name")) {
            this.filterStartsWithNameCheck(parameter);
        }
        if (name.equals("remove_if_lowe")) {
            this.insertKeyCheck(parameter);
        }
        if (name.equals("remove")) {
            this.removeKeyCheck(parameter);
        }
        if (name.equals("replace_if_lowe")) {
            this.insertKeyCheck(parameter);
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
            } else {
                this.insertKeyCheck(key);
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
            } else {
                this.countByGenreCheck(key);
            }
        }
    }

    public void executeScriptCheck(String parameter) {
        String fileName = parameter;
        if (parameter == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название файла");
            System.out.print("$ ");
            fileName = scanner.nextLine();
            if (fileName.isEmpty()) {
                System.out.println("Название файла не может быть null");
            } else {
                this.executeScriptCheck(fileName);
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
            } else {
                this.filterStartsWithNameCheck(key);
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
            } else {
                this.removeKeyCheck(key);
            }
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
}