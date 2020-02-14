package commands;

import java.util.*;

public class Commands {
    private Map<String, ICommand> commands = new TreeMap<>();

    public void addNewCommand(String name, ICommand command){
        commands.put(name, command);
    }

    public Commands(String name, String parameter){
       commands.get(name).Do(parameter);
    }


}
