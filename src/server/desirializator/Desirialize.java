package server.desirializator;
import java.io.*;
public class Desirialize {
    public Desirialize() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("client/tempFile");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        //Object object = (Object) objectInputStream.readObject();
        objectInputStream.close();
    }
}
