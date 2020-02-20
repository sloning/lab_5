package commands;

import java.io.IOException;

public interface ICommand {
    void Do(String parameter1, String parameter2) throws IOException;
}
