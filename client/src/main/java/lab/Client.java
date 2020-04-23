package lab;

import client_controller.Validation;
import input_output.FabricOfShell;
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
        Socket socket = new Socket("127.0.0.1", 1111);

        System.out.println("Подключился");
        while (true) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStream serializer = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
            InputStream inputStream = socket.getInputStream();

            InputOutput inputOutput = new InputOutput();
            inputOutput.Input();
            Shell shell = inputOutput.getShell();
            out.writeObject(shell);

//                if (Validation.getSignal()) {
//                    Shell shell = inputOutput.getShell();
//                    out.writeObject(shell);
//                } else {
//                    FabricOfShell fabricOfShell = new FabricOfShell();
//                    for (int i = 0; i < fabricOfShell.getSize(); i++) {
//                        out.writeObject(fabricOfShell.getShell(i));
//                    }
//                    Validation.setSignal(false);
//                    fabricOfShell.clearCollection();
//                }

            byte[] outBytes = byteArrayOutputStream.toByteArray();
            serializer.write(outBytes);
            serializer.flush();
            System.out.println("Отправил сообщение");

            ByteBuffer inBuffer = ByteBuffer.allocate(10000000);
            int quantityOfReadBytes = inputStream.read(inBuffer.array());
            byte[] inBytes = new byte[10000000];
            inBuffer.rewind();
            inBuffer.get(inBytes, 0, quantityOfReadBytes);
            inBuffer.clear();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            String answer = (String) objectInputStream.readObject();
            System.out.println(answer);
        }
    }
}
