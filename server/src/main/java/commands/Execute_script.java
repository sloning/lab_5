package commands;


import movie.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Executes script
 *
 * @author Abay geniy
 */
public class Execute_script implements ICommand {
    /**
     * @param name name of command
     */
    private static String fileName;
    private static boolean signal = false;
    private String name;
    private List<String> fileNames = new ArrayList<>();

    public Execute_script() {
        name = "execute_script";
        Commands.addNewCommand("execute_script", this);
    }

    public static boolean getSignal() {
        return signal;
    }

    public static String getFileName() {
        return fileName;
    }

    @Override
    public String Do(String parameter, Movie movie) throws IOException {
        return null;
    }

    //TODO переделать, по сути просто с другого класса читать

    /**
     * get info about command
     *
     * @return String
     */
    public String info() {
        return name + ": считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }
}