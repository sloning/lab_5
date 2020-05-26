package lab;

import Collection.SaveCollection;
import socket_channel_connection.Connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

public class Server {
    private static Selector selector = null;
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    ServerSocketChannel socket = ServerSocketChannel.open();
    ServerSocket serverSocket = socket.socket();
    Connection connection = new Connection();
    SaveCollection saveCollection = new SaveCollection();
    SelectionKey key;
    String response = "";

    public Server(int port, int max_threads) throws Exception {
        try {
            selector = Selector.open();
            serverSocket.bind(new InetSocketAddress("localhost", port));
            socket.configureBlocking(false);
            int ops = socket.validOps();
            socket.register(selector, ops, null);
            saveCollection.checkForSaveCommand();
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
                        ReadHandler read = new ReadHandler(key, connection, max_threads);
                        FutureTask<String> futureTask = new FutureTask<String>(read);
                        Thread t = new Thread(futureTask);
                        t.start();
                        response = futureTask.get();
                        key.channel().register(selector, SelectionKey.OP_WRITE);
                        if (response == null) {
                            iterator.remove();
                            continue;
                        }
                    }
                    if (key.isWritable()) {
                        //ForkJoinPool
                        WriteHandler write = new WriteHandler(key, response, connection);

                        key.channel().register(selector, SelectionKey.OP_READ);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Соединение разорвано");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Нет доступных подключений");
        }
    }

    private void handleAccept() throws IOException {
        LOGGER.info("Соединение установлено");

        SocketChannel client = socket.accept();
        client.configureBlocking(false);

        client.register(selector, SelectionKey.OP_READ);
    }
}
