package commands;

import java.util.Arrays;
import java.util.Map;

public class Help implements ICommand {
    public Help(Commands com){
        com.addNewCommand("help", this);
    }

    @Override
    public void Do(String parameter1, String parameter2){
        System.out.println("help: вывести справку по доступным командам");
        System.out.println("info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д." );
        System.out.println("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("insert key {element}: добавить новый элемент с заданным ключом");
        System.out.println("update id {element}: обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_key key: удалить элемент из коллекции по его ключу");
        System.out.println("clear: очистить коллекцию");
        System.out.println("save: сохранить коллекцию в файл");
        System.out.println("execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        System.out.println("exit: завершить программу (без сохранения в файл)");
        System.out.println("history: вывести последние 6 команд (без их аргументов)");
        System.out.println("replace_if_lowe key {element}: заменить значение по ключу, если новое значение меньше старого");
        System.out.println("min_by_id: вывести любой объект из коллекции, значение поля id которого является минимальным");
        System.out.println("cout_by_genre genre: вывести количество элементов, значение поля genre которых равно заданному");
        System.out.println("filter_starts_with_name name: вывести элементы, значение поля name которых начинается с заданной подстроки");
    }

}
