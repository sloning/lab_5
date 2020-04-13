package client.commands;

import java.io.IOException;

public interface ICommand {
    void Do(String parameter) throws Exception;
    String info();
}

