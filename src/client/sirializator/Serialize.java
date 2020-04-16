package client.sirializator;

import java.io.*;

public class Serialize {
    public Serialize(SerializedObject serializedObject) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serializedObject);
        objectOutputStream.flush();
    }
}
