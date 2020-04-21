package lab;

import input_output.InputOutput;
import data.Shell;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception {
        try (
                Socket socket = new Socket("127.0.0.1", 1111);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStream serializer = socket.getOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        ) {
            System.out.println("Подключился");
            InputOutput inputOutput = new InputOutput();
            inputOutput.Input();
            Shell shell = inputOutput.getShell();
            out.writeObject(shell);
            byte[] outBytes = byteArrayOutputStream.toByteArray();
            serializer.write(outBytes);
            serializer.flush();
            System.out.println("Отправил сообщение");


            InputStream inputStream = socket.getInputStream();
            ByteBuffer outBuffer = ByteBuffer.allocate(10000000);
            int quantityOfReadBytes = inputStream.read(outBuffer.array());
            byte[] inBytes = new byte[1000000];
            outBuffer.rewind();
            outBuffer.get(inBytes, 0, quantityOfReadBytes);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            String answer = (String) objectInputStream.readObject();
            System.out.println(answer);
        } catch (Throwable cause) {
            cause.printStackTrace();
//            System.out.println("Ошибка подключения: " + cause.getMessage());
        }


    }
}