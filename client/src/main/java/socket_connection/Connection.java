package socket_connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Connection {
    public void write(byte[] bytes, Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes);
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
            System.err.println("Reached an end of inputStream: " + socket.getInetAddress().getHostAddress());
        }

        byte[] bytes = new byte[bufferSize];
        outBuffer.rewind();
        outBuffer.get(bytes, 0, quantityOfReadBytes);
        outBuffer.clear();

        return bytes;
    }
}
