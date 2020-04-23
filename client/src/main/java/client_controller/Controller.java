package client_controller;

import data.Shell;
import command_history.CommandHistory;
import input_output.InputOutput;
import movie.Movie;

import java.io.IOException;
import java.util.Scanner;

/**
 * Process commands and pass it to command module
 *
 * @author Abay
 */
public class Controller {
    Movie movie = null;
    Shell shell = null;
    String name = null;
    String parameter = null;
    /**
     * Splits string with commands to command name and parameters
     *
     * @param command command to process
     */
    public Controller(String command) throws IOException {

        String[] nameCommands = new String[2];
        nameCommands = command.split(" ");

        if (nameCommands.length<=2) {

            Validation validation = new Validation(nameCommands[0]);

            if (nameCommands.length == 2) {
                validation.setParameter(nameCommands[1]);
            }

            validation.check();

            this.name = validation.getName();
            this.parameter = validation.getParameter();
            this.movie = validation.getMovie();

            shell = new Shell(name, parameter, movie);


            CommandHistory commandHistory = new CommandHistory();
            commandHistory.addCommand(nameCommands[0]);
        } else {
            System.out.println("Введено больше допустимого количество аргументов");
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Введите команду");
                System.out.print("$ ");
                String nameofcommand = sc.nextLine();
                Controller controller = new Controller(nameofcommand);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                System.out.println("Вы ввели неверное название команды (введите help, чтобы получить справку по доступным командам)");
            }
        }
    }

    public Shell getShell(){
        return shell;
    }
}
