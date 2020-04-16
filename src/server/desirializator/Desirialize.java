package server.desirializator;
import java.io.*;
public class Desirialize {
    public Desirialize(ByteArrayOutputStream  byteArrayOutputStream) throws Exception{
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Object object = (Object) objectInputStream.readObject();
        objectInputStream.close();
    }
}
