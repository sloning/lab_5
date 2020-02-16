package commands;

import java.util.Arrays;
import java.util.Map;

public class Help implements ICommand {
    public Help(Commands com){
        com.addNewCommand("help", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        System.out.println("Help: вывести справку по доступным командам");
        System.out.println("Info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д." );
    }

}
