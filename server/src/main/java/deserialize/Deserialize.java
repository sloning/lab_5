package server.src.main.java.deserialize;

import common.data.Shell;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserialize {
    public Deserialize(String name) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(name);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Shell shell = (Shell) objectInputStream.readObject();
        objectInputStream.close();
    }
}
