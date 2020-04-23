package socket_connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class Connection {
    public void write(byte[] bytes, Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes);
        } catch (SocketException e) {
            System.err.println("Соеденение с сервером потеряно\nКоманда не передана\nОтключаюсь");
            System.exit(0);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            System.err.println("Ошибка записи");
        }
    }

    public byte[] read(Socket socket) {
        int quantityOfReadBytes = 0;
        int bufferSize = 10000000;
        ByteBuffer outBuffer = ByteBuffer.allocate(bufferSize);
        try {
            InputStream inputStream = socket.getInputStream();
            quantityOfReadBytes = inputStream.read(outBuffer.array());
        } catch (SocketException e) {
            System.err.println("Соеденение с сервером потеряно\nОтключаюсь");
            System.exit(0);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            System.err.println("Ошибка чтения");
        }

        if (quantityOfReadBytes == -1) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.err.println("Достигнут конец inputStream'а: " + socket.getInetAddress().getHostAddress());
        }

        byte[] bytes = new byte[bufferSize];
        outBuffer.rewind();
        outBuffer.get(bytes, 0, quantityOfReadBytes);
        outBuffer.clear();

        return bytes;
    }
}
