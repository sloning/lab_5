package controller;

import data.Shell;
import command_history.CommandHistory;
import input_output.FabricOfMovies;
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
        nameCommands = command.split(" ");

        Validation validation = new Validation(nameCommands[0], nameCommands[1]);
        this.name = validation.getName();
        this.parameter = validation.getParameter();
        this.movie = validation.getMovie();

        if (parameter.equals(null)) {
            shell = new Shell(name, null, movie);
        } else {
            shell = new Shell(name, parameter, movie);
        }


        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand(nameCommands[0]);
    }

    public Shell getShell(){
        return shell;
    }
}
