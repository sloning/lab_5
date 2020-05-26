package lab;

import client_controller.Validation;
import data.FabricOfShell;
import data.Shell;
import data.UserShell;
import input_output.CollectionOfShells;
import input_output.InputOutput;
import registration.Registration;
import serializer.Serializer;
import socket_connection.Connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class ClientMain {
    private static final int maxReconnectionAttempts = 3;
    private static int reconnectionAttempts = 0;
    private static final int msToReconnect = 3000;

    public static void main(String[] args) throws Exception {
        while (true) {
            try (Socket socket = new Socket("localhost", 1111)) {
                System.out.println("Подключение успешно совершено");
                clientRun(socket);
            } catch (ConnectException e) {
                System.err.println("Ошибка подключения к серверу");
                if (++reconnectionAttempts >= maxReconnectionAttempts) {
                    System.err.println("Превышено количество попыток подключения");
                    System.exit(0);
                } else {
                    System.err.println("Повторое подключение будет совершено через " + msToReconnect / 1000 + " секунды");
                    Thread.sleep(msToReconnect);
                }
            }
        }
    }

    private static void clientRun(Socket socket) throws IOException, NullPointerException {
        InputOutput inputOutput = new InputOutput();
        Serializer serializer = new Serializer();
        Connection connection = new Connection();
        Registration registration = new Registration();
        String user = null;
        boolean logged = false;
        while (!logged) {
            registration.authorization();
            UserShell userShell = registration.getUserShell();
            connection.write(serializer.toByteArray(userShell), socket);
            byte[] inputBytes;
            inputBytes = connection.read(socket);
            String answer = serializer.fromByteArray(inputBytes, String.class);
            if (!answer.equals("null") && answer.equals("Авторизация прошла успешно")) {    //TODO fix crash if server stops here
                logged = true;
                user = userShell.getLogin();
            }
            System.out.println(answer);
        }

        while (true) {
            inputOutput.Input();
            inputOutput.setUser(user);
            if (!Validation.getSignal()) {
                if (sendMsg(inputOutput, serializer, connection, socket)) break;
            } else {
                if (sendMsgWithScript(serializer, connection, socket)) break;
            }
            if (readMsg(serializer, connection, socket)) break;
        }
    }

    private static boolean sendMsg(InputOutput inputOutput, Serializer serializer, Connection connection, Socket socket) {
        Shell shell = inputOutput.getShell();
        if (Validation.sendReady) {
            connection.write(serializer.toByteArray(shell), socket);
            if (Connection.connFlag) {
                System.out.println("Сообщение отправлено");
            } else return true;
        }
        return false;
    }

    private static boolean sendMsgWithScript(Serializer serializer, Connection connection, Socket socket) {
        FabricOfShell fabricOfShell = new FabricOfShell();
        CollectionOfShells collectionOfShells = new CollectionOfShells();
        fabricOfShell.setShellCollection(collectionOfShells.getShellCollection());
        if (Validation.sendReady) {
            connection.write(serializer.toByteArray(fabricOfShell), socket);
            if (Connection.connFlag) {
                System.out.println("Сообщение отправлено");
            } else return true;
        }
        Validation.setSignal(false);
        collectionOfShells.clearCollection();
        return false;
    }

    private static boolean readMsg(Serializer serializer, Connection connection, Socket socket) {
        byte[] inputBytes;
        inputBytes = connection.read(socket);
        if (inputBytes == null) return true;
        String answer = serializer.fromByteArray(inputBytes, String.class);
        if (answer != null) System.out.println(answer);
        else return true;
        return false;
    }
}
