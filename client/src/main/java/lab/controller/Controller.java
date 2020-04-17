package src.main.java.lab.controller;

import src.main.java.lab.data.CommandHistory;
import src.main.java.lab.input_output.FabricOfMovies;
import src.main.java.lab.movie.Movie;
import src.main.java.lab.query.Serialize;
import src.main.java.lab.query.Shell;

import java.io.IOException;

/**
 * Process commands and pass it to command module
 *
 * @author Abay
 */
public class Controller {
    /**
     * Splits string with commands to command name and parameters
     *
     * @param command command to process
     */
    public Controller(String command) throws IOException {
        Movie movie = null;
        Shell shell = null;
        String[] nameCommands = new String[2];
        nameCommands = command.split(" ");
        if ((nameCommands[0].equals("insert")) || (nameCommands[0].equals("update_id"))) {
            FabricOfMovies fabricOfMovies = new FabricOfMovies();
            movie = fabricOfMovies.create();
        }

        if (nameCommands.length == 1) {
            shell = new Shell(nameCommands[0], null, movie);
        } else if (nameCommands.length == 2) {
            shell = new Shell(nameCommands[0], nameCommands[1], movie);
        }

        Serialize serialize = new Serialize(shell);

        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand(nameCommands[0]);
    }
}
