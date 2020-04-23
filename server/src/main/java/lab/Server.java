package lab;

import Collection.SaveMovies;
import commands.Commands;
import data.FabricOfShell;
import data.Shell;
import serializer.Serializer;
import socket_channel_connection.Connection;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class Server {
    private static Selector selector = null;
    static Logger LOGGER = Logger.getLogger(Server.class.getName());

    public Server() throws Exception {
        try {
            selector = Selector.open();
            ServerSocketChannel socket = ServerSocketChannel.open();
            ServerSocket serverSocket = socket.socket();
            serverSocket.bind(new InetSocketAddress("localhost", 1111));
            socket.configureBlocking(false);
            int ops = socket.validOps();
            socket.register(selector, ops, null);
            Serializer serializer = new Serializer();
            Connection connection = new Connection();
            SaveMovies saveMovie = new SaveMovies();
            saveMovie.checkForSaveCommand();
            String response = null;
            LOGGER.info("Сервер готов к работёнке");

            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        handleAccept(socket);
                    }
                    if (key.isReadable()) {
                        response = handleRead(key, serializer, connection);
                        if (response == null) {
                            iterator.remove();
                            continue;
                        }
                    }
                    if (key.isWritable()) {
                        handleWrite(key, response, serializer, connection);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Соединение разорвано");
        }
    }

    private void handleAccept(ServerSocketChannel mySocket) throws IOException {
        LOGGER.info("Соединение установлено");

        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);

        client.register(selector, SelectionKey.OP_READ);
    }

    private String handleRead(SelectionKey key, Serializer serializer, Connection connection) throws Exception {
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
                LOGGER.info("начало работы со скриптом");
                for (int i = 0; i < fabricOfShell.getSize(); i++) {
                    Shell shell = fabricOfShell.getShell(i);
                    Commands useCommands = new Commands(shell.getName(), shell.getParameter(), shell.getMovie());
                    LOGGER.info("Получена команда: " + shell.getName());
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                    result += useCommands.execute();
                }
                return result;
            } else return null;
        } catch (NullPointerException e) {
            System.err.println("Соединение с клиентом неожиданно (нет) потеряно");
            return null;
        }
    }

    private void handleWrite(SelectionKey key, String response, Serializer serializer, Connection connection) throws IOException {
        LOGGER.info("Пишу ответочку");
        SocketChannel socketChannel = (SocketChannel) key.channel();

        connection.write(serializer.toByteArray(response), socketChannel);
        LOGGER.info("Отправлен ответ: " + response);

        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
