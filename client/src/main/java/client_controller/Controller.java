package client_controller;

import command_history.CommandHistory;
import data.Shell;
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
    public boolean commandController(String command) throws IOException {
        CommandHistory commandHistory = new CommandHistory();
        String[] nameCommands = new String[2];
        nameCommands = command.split(" ");

        if (nameCommands.length <= 2) {
            Validation validation = new Validation(nameCommands[0]);

            if (nameCommands.length == 2) {
                validation.setParameter(nameCommands[1]);
            }

            if (validation.check()) {
                commandHistory.addCommand(nameCommands[0]);
                this.name = validation.getName();
                this.parameter = validation.getParameter();
                this.movie = validation.getMovie();

                if (!nameCommands[0].equals("history")) {
                    shell = new Shell(name, parameter, movie);
                    return true;
                } else {
                    System.out.println(commandHistory.printHistory());
                    return false;
                }

            } else {
                return false;
            }
        } else {
            System.out.println("Введено больше допустимого количество аргументов");
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Введите команду");
                System.out.print("$ ");
                String nameofcommand = sc.nextLine();
                Controller controller = new Controller();
                controller.commandController(nameofcommand);
                shell = controller.getShell();
                commandHistory.addCommand(nameCommands[0]);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                System.out.println("Вы ввели неверное название команды (введите help, чтобы получить справку по доступным командам)");
            }
        }
        return false;
    }

    public Shell getShell(){
        return shell;
    }

    public void setUser(String user) {
        this.shell.setUser(user);
    }

    public void setPassword(String password) {
        this.shell.setPassword(password);
    }
}
