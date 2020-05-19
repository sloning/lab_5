package lab;

import Collection.SaveCollection;
import commands.Commands;
import data.FabricOfShell;
import data.Shell;
import serializer.Serializer;
import socket_channel_connection.Connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class Server {
    private static Selector selector = null;
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    // Все вынес сюда, надеюсь, что многопоточности не повредит.
    ServerSocketChannel socket = ServerSocketChannel.open();
    ServerSocket serverSocket = socket.socket();
    Serializer serializer = new Serializer();
    Connection connection = new Connection();
    SaveCollection saveCollection = new SaveCollection();
    SelectionKey key;
    String response;
    DB_Connection db_connection;

    public Server(String db_url, String db_user, String db_pass, int port) throws Exception {
        db_connection = new DB_Connection(db_url, db_user, db_pass);
        try {
            selector = Selector.open();
            serverSocket.bind(new InetSocketAddress("localhost", port));
            socket.configureBlocking(false);
            int ops = socket.validOps();
            socket.register(selector, ops, null);
            saveCollection.checkForSaveCommand();
            response = null;
            LOGGER.info("Сервер готов к работе");

            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    key = iterator.next();

                    if (key.isAcceptable()) {
                        handleAccept();
                    }
                    if (key.isReadable()) {
                        response = handleRead();
                        if (response == null) {
                            iterator.remove();
                            continue;
                        }
                    }
                    if (key.isWritable()) {
                        handleWrite();
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Соединение разорвано");
        } finally {
            db_connection.close();
        }
    }

    private void handleAccept() throws IOException {
        LOGGER.info("Соединение установлено");

        SocketChannel client = socket.accept();
        client.configureBlocking(false);

        client.register(selector, SelectionKey.OP_READ);
    }

    private String handleRead() throws Exception {
        try {
            LOGGER.info("Читаю");
            SocketChannel socketChannel = (SocketChannel) key.channel();
            byte[] byteArray = connection.read(socketChannel);
            if (byteArray == null) return null;
            int flag = serializer.checkByteArray(byteArray);
            if (flag == 1) {
                Shell shell = serializer.fromByteArray(byteArray, Shell.class);
                Commands useCommands = new Commands(shell.getName(), shell.getParameter(), shell.getMovie());
                LOGGER.info("Получена команда: " + shell.getName());
                socketChannel.register(selector, SelectionKey.OP_WRITE);
                return useCommands.execute();
            } else if(flag == 0) {
                String result = "";
                FabricOfShell fabricOfShell = serializer.fromByteArray(byteArray, FabricOfShell.class);
                LOGGER.info("Начало работы со скриптом");
                for (int i = 0; i < fabricOfShell.getSize(); i++) {
                    Shell shell = fabricOfShell.getShell(i);
                    Commands useCommands = new Commands(shell.getName(), shell.getParameter(), shell.getMovie());
                    LOGGER.info("Получена команда: " + shell.getName());
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                    result += useCommands.execute();
                }
                return result;
            } else return null;
        } catch (NullPointerException | ClosedChannelException e) {
            System.err.println("Соединение с клиентом неожиданно (нет) потеряно");
            return null;
        }
    }

    private void handleWrite() throws IOException {
        LOGGER.info("Пишу ответочку");
        SocketChannel socketChannel = (SocketChannel) key.channel();

        connection.write(serializer.toByteArray(response), socketChannel);
        LOGGER.info("Отправлен ответ: " + response);

        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
