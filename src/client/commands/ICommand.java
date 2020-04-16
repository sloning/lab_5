package client.commands;

import java.io.IOException;

public interface ICommand {
    void Do(String parameter1) throws IOException;

    String info();
}

