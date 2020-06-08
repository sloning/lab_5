package lab;

import client_controller.Validation;
import data.FabricOfShell;
import data.Shell;
import input_output.CollectionOfShells;
import input_output.InputOutput;
import serializer.Serializer;
import socket_connection.Connection;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {
    private static final int maxReconnectionAttempts = 3;
    private static final int msToReconnect = 3000;
    public static String user = null;
    public static String password = null;
    public static Socket socket = null;
    private static int reconnectionAttempts = 0;
    InputOutput inputOutput = new InputOutput();
    Serializer serializer = new Serializer();
    Connection connection = new Connection();

    @Override
    public void run() {
        while (true) {
            try (Socket trialSocket = new Socket("localhost", 1111)) {
                socket = trialSocket;
                System.out.println("Подключение успешно совершено");
                clientRun(socket);
            } catch (IOException e) {
                System.err.println("Ошибка подключения к серверу");
                if (++reconnectionAttempts >= maxReconnectionAttempts) {
                    System.err.println("Превышено количество попыток подключения");
                    System.exit(0);
                } else {
                    System.err.println("Повторое подключение будет совершено через " + msToReconnect / 1000 + " секунды");
                    try {
                        Thread.sleep(msToReconnect);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }
    }

    private void clientRun(Socket socket) throws IOException, NullPointerException {
        while (true) {
            inputOutput.Input();
            inputOutput.setUser(user);
            inputOutput.setPassword(password);
            if (!Validation.getSignal()) {
                if (sendMsg(inputOutput, serializer, connection, socket)) break;
            } else {
                if (sendMsgWithScript(serializer, connection, socket)) break;
            }
            if (readMsg(serializer, connection, socket)) break;
        }
    }

    public String clientOneCommand(String text, String user, String password) {
        InputOutput inputOutput = new InputOutput();
        try {
            inputOutput.InputOneCommand(text);
            inputOutput.setUser(user);
            inputOutput.setPassword(password);
            if (!Validation.getSignal()) {
                boolean c = sendMsg(inputOutput, serializer, connection, socket);
            } else {
                boolean g = (sendMsgWithScript(serializer, connection, socket));
            }
            byte[] inputBytes;
            inputBytes = connection.read(socket);
            if (inputBytes == null) System.out.println("ноу");
            String answer = serializer.fromByteArray(inputBytes, String.class);
            if (answer != null) System.out.println(answer);
            return answer;
        } catch (IOException e) {
            System.err.println("Ошибка подключения к серверу");
            if (++reconnectionAttempts >= maxReconnectionAttempts) {
                System.err.println("Превышено количество попыток подключения");
                System.exit(0);
            } else {
                System.err.println("Повторое подключение будет совершено через " + msToReconnect / 1000 + " секунды");
                try {
                    Thread.sleep(msToReconnect);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

            return "упс";
        }
    }

    private boolean sendMsg(InputOutput inputOutput, Serializer serializer, Connection connection, Socket socket) {
        Shell shell = inputOutput.getShell();
        if (Validation.sendReady) {
            connection.write(serializer.toByteArray(shell), socket);
            if (Connection.connFlag) {
                System.out.println("Сообщение отправлено");
            } else return true;
        }
        return false;
    }

    private boolean sendMsgWithScript(Serializer serializer, Connection connection, Socket socket) {
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

    private boolean readMsg(Serializer serializer, Connection connection, Socket socket) {
        byte[] inputBytes;
        inputBytes = connection.read(socket);
        if (inputBytes == null) return true;
        String answer = serializer.fromByteArray(inputBytes, String.class);
        if (answer != null) System.out.println(answer);
        else return true;
        return false;
    }
}
