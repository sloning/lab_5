package lab;

import input_output.InputOutput;
import data.Shell;
import serializer.Serializer;
import socket_connection.Connection;

import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 1111);

        System.out.println("Подключение успешно совершено");
        InputOutput inputOutput = new InputOutput();
        while (true) {
            inputOutput.Input();
            Shell shell = inputOutput.getShell();
            Connection.write(Serializer.toByteArray(shell), socket);
            System.out.println("Сообщение отправлено");

            String answer = Serializer.fromByteArray(Connection.read(socket), String.class);
            System.out.println(answer);

//                if (Validation.getSignal()) {
//                    Shell shell = inputOutput.getShell();
//                    out.writeObject(shell);
//                } else {
//                    FabricOfShell fabricOfShell = new FabricOfShell();
//                    for (int i = 0; i < fabricOfShell.getSize(); i++) {
//                        out.writeObject(fabricOfShell.getShell(i));
//                    }
//                    out.writeObject(fabricOfShell);
//                    Validation.setSignal(false);
//                    fabricOfShell.clearCollection();
//                }
        }
    }
}
