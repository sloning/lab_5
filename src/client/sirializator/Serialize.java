package client.sirializator;

import client.commands.ICommand;

import java.io.*;

public class Serialize {
    public Serialize(ICommand command) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("client/tempFile");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(command);
        objectOutputStream.close();
    }


}
