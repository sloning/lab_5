package lab;

import input_output.InputOutput;
import data.Shell;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            // String[] messages = {"I like non-blocking servers", "Hello non-blocking world!", "One more message..", "exit"};
            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 1111));
            InputOutput inputOutput = new InputOutput();
            // Using this to send stuff
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            // Using this to receive stuff
            ByteBuffer data = ByteBuffer.allocate(1024);

//            for (String msg : messages) {
//                System.out.println("Prepared message: " + msg);
//                ByteBuffer buffer = ByteBuffer.allocate(1024);
//                buffer.put(msg.getBytes());
//                buffer.flip();
//                int bytesWritten = client.write(buffer);
//                System.out.println(String.format("Sending Message: %s\nbufforBytes: %d", msg, bytesWritten));
//            }

            while (true) {
                inputOutput.Input();
                objectOutputStream.writeObject(inputOutput.getShell());
                objectOutputStream.flush();
                client.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
                System.out.println("Сообщение отправлено");

                System.out.println("Жду сообщение");
                client.read(data);
                System.out.println("Сообщение принято");
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.array());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                String response = (String) objectInputStream.readObject();
                System.out.println(response);
            }
        } catch (ConnectException e1) {
            System.out.println("Ошибка подключения");
            System.exit(0);
        } catch (EOFException e2) {
            System.out.println("EOF");
            System.exit(0);
        }
    }
}