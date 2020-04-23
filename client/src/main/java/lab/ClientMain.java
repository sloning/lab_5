package lab;

import client_controller.Validation;
import data.FabricOfShell;
import input_output.CollectionOfShells;
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
        Serializer serializer = new Serializer();
        Connection connection = new Connection();
        while (true) {
            inputOutput.Input();
            if (!Validation.getSignal()) {
                Shell shell = inputOutput.getShell();
                connection.write(serializer.toByteArray(shell), socket);
            } else {
                FabricOfShell fabricOfShell = new FabricOfShell();
                CollectionOfShells collectionOfShells = new CollectionOfShells();
                fabricOfShell.setShellCollection(collectionOfShells.getShellCollection());
                connection.write(serializer.toByteArray(fabricOfShell), socket);
                Validation.setSignal(false);
                collectionOfShells.clearCollection();
            }
            System.out.println("Сообщение отправлено");

            String answer = serializer.fromByteArray(connection.read(socket), String.class);
            System.out.println(answer);

        }
    }
}
