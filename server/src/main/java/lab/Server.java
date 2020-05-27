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
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Server {
    static Selector selector = null;
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    ServerSocketChannel socket = ServerSocketChannel.open();
    ServerSocket serverSocket = socket.socket();
    Connection connection = new Connection();
    SaveCollection saveCollection = new SaveCollection();
    SelectionKey key;
    String response = "1";

    public Server(int port, int max_threads) throws Exception {
        try {
            selector = Selector.open();
            serverSocket.bind(new InetSocketAddress("localhost", port));
            socket.configureBlocking(false);
            int ops = socket.validOps();
            socket.register(selector, ops, null);
            saveCollection.checkForSaveCommand();
            ForkJoinPool pool = new ForkJoinPool();
            LOGGER.info("Сервер готов к работе");
            ReentrantLock locker = new ReentrantLock();

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
                        ReadHandler read = new ReadHandler(key, connection, max_threads, selector);
                        FutureTask<String> futureTask = new FutureTask<String>(read);
                        Thread t = new Thread(futureTask);
                        System.out.println("server thread in read: " + Thread.currentThread());
                        t.start();
                        response = futureTask.get();
                        if (response == null) {
                            iterator.remove();
                            continue;
                        }
                    }
                    if (key.isWritable() && !response.equals("1")) {     //TODO test requests from several clients
                        System.out.println("server thread in write (before fork join): " + Thread.currentThread());
                        try {
                            ForkJoinPool.managedBlock(new ManagedLocker(locker));
                            WriteHandler write = new WriteHandler(key, response, connection, selector);
                            pool.execute(write);
                        } finally {
                            locker.unlock();
                        }
                        response = "1";
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
