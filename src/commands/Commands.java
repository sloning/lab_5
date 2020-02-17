package commands;

import java.util.*;

public class Commands {
    protected static Map<String, ICommand> commands = new TreeMap<>();

    public static void addNewCommand(String name, ICommand command){
        commands.put(name, command);
    }

    public void outCommand(ICommand command){
        commands.get(command);
    }

    public Commands(String name, String parameter1, String parameter2){
       commands.get(name).Do(parameter1, parameter2);
    }

}
