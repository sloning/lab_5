package controller;
import java.util.*;
import commands.*;

public class Controller {
    public void Controller(String command){
        String[] nameCommands = new String[1];
        nameCommands=command.split(" ");
        if (nameCommands.length==1) {
            Commands useCommands = new Commands(nameCommands[0], null);
        } else {
            Commands useCommands = new Commands(nameCommands[0], nameCommands[1]);
        }
    }


}
