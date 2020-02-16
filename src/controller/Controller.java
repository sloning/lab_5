package controller;
import java.util.*;
import commands.*;

public class Controller {
    public void Controller(String command){
        String[] nameCommands = new String[2];
        nameCommands=command.split(" ");
        if (nameCommands.length==1) {
            Commands useCommands = new Commands(nameCommands[0], null, null);
        } else if (nameCommands.length==2) {
            Commands useCommands = new Commands(nameCommands[0], nameCommands[1], null);
        } else {
            Commands useCommands = new Commands(nameCommands[0], nameCommands[1], nameCommands[2]);
        }
    }
}
