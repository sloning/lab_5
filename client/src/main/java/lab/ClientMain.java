package lab;

import client_controller.Validation;
import data.FabricOfShell;
import input_output.CollectionOfShells;
import input_output.InputOutput;
import data.Shell;
import serializer.Serializer;
import socket_connection.Connection;

import java.net.ConnectException;
import java.net.Socket;

public class ClientMain {
    private static final int maxReconnectionAttempts = 3;
    private static int reconnectionAttempts = 0;
    private static int msToReconnect = 3000;

    public static void main(String[] args) throws Exception {
        while (true) {
            try (Socket socket = new Socket("127.0.0.1", 1111);) {
                System.out.println("Подключение успешно совершено");
                InputOutput inputOutput = new InputOutput();
                Serializer serializer = new Serializer();
                Connection connection = new Connection();
                while (true) {
                    inputOutput.Input();
                    if (!Validation.getSignal()) {
                        Shell shell = inputOutput.getShell();
                        if (Validation.sendReady) {
                            connection.write(serializer.toByteArray(shell), socket);
                            System.out.println("Сообщение отправлено");

                            String answer = serializer.fromByteArray(connection.read(socket), String.class);
                            if (answer != null) System.out.println(answer);
                            else System.out.println("Сервер предпочёл промолчать");
                        }
                    } else {
                        FabricOfShell fabricOfShell = new FabricOfShell();
                        CollectionOfShells collectionOfShells = new CollectionOfShells();
                        fabricOfShell.setShellCollection(collectionOfShells.getShellCollection());
                        connection.write(serializer.toByteArray(fabricOfShell), socket);
                        Validation.setSignal(false);
                        collectionOfShells.clearCollection();
                        System.out.println("Сообщение отправлено");

                        String answer = serializer.fromByteArray(connection.read(socket), String.class);
                        if (answer != null) System.out.println(answer);
                        else System.out.println("Сервер предпочёл промолчать");
                    }
                }
            } catch (ConnectException e) {
                System.err.println("Ошибка подключения к серверу");
                if (++reconnectionAttempts >= maxReconnectionAttempts)
                {
                    System.err.println("Превышено количество попыток подключения");
                    System.exit(0);
                } else {
                    System.err.println("Повторое подключение будет совершено через " + msToReconnect/1000 + " секунды");
                    Thread.sleep(msToReconnect);
                }
            }
        }
    }
}
