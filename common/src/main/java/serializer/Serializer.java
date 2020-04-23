package serializer;

import data.Shell;

import java.io.*;

public class Serializer {
    public byte[] toByteArray(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream)) {
            out.writeObject(object);
        } catch (IOException e) {
            System.err.println("Ошибка сериализации");
        }
        return byteArrayOutputStream.toByteArray();
    }

    public <T> T fromByteArray(byte[] bytes, Class<T> clazz) throws IOException {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream)) {
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            System.err.println("Ошибка десериализации");
        }
        return null;
    }

    public int checkByteArray(byte[] bytes) throws IOException, ClassNotFoundException {
        try(
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
        ) {
            Object object = objectInputStream.readObject();
            if (Shell.class.isInstance(object)) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка десериализации");
        }
        return -1;
    }
}
