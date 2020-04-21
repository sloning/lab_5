package client_controller;

import data.Shell;
import command_history.CommandHistory;
import movie.Movie;

import java.io.IOException;

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
        nameCommands = command.split(" ");      // TODO добавить исключение, если в команде будет 3+ слова

        Validation validation = new Validation(nameCommands[0], null);

        if (nameCommands.length == 2) {
            validation.setParameter(nameCommands[1]);
        }
        this.name = validation.getName();
        this.parameter = validation.getParameter();
        this.movie = validation.getMovie();

        shell = new Shell(name, parameter, movie);


        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand(nameCommands[0]);     // TODO сделать статиком
    }

    public Shell getShell(){
        return shell;
    }
}
