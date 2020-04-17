package src.main.java.lab.query;

import java.io.*;

public class Serialize {
    public Serialize(Object object) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("tempFile");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }
}
