package serializer;

import data.Shell;

import java.io.*;

public class Serializer {
    public byte[] toByteArray(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream)) {
            out.writeObject(object);
        } catch (IOException e) {
            System.out.println("Ошибка сериализации");
        }
        return byteArrayOutputStream.toByteArray();
    }

    public <T> T fromByteArray(byte[] bytes, Class<T> clazz) throws IOException {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream)) {
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Ошибка десериализации");
        }
        return null;
    }
}
