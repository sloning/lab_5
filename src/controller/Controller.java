package controller;
import commands.Commands;
import data.CommandHistory;

import java.io.IOException;

/**
 * Process commands and pass it to command module
 * @author Abay
 */
public class Controller {
    /**
     * Splits string with commands to command name and parameters
     * @param command command to process
     */
    public Controller(String command) throws IOException {
        String[] nameCommands = new String[2];
        nameCommands=command.split(" ");
        if (nameCommands.length==1) {
            Commands useCommands = new Commands(nameCommands[0], null, null);
        } else if (nameCommands.length==2) {
            Commands useCommands = new Commands(nameCommands[0], nameCommands[1], null);
        } else {
            Commands useCommands = new Commands(nameCommands[0], nameCommands[1], nameCommands[2]);
        }
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand(nameCommands[0]);
    }
}
