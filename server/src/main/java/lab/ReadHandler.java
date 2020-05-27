package lab;

import serializer.Serializer;
import socket_channel_connection.Connection;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class ReadHandler implements Callable<String> {
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    SelectionKey key;
    Connection connection;
    int max_threads;
    Selector selector;

    public ReadHandler(SelectionKey key, Connection connection, int max_threads, Selector selector) {
        this.key = key;
        this.connection = connection;
        this.max_threads = max_threads;
        this.selector = selector;
    }

    @Override
    public String call() throws IOException {
        Serializer serializer = new Serializer();
        try {
            System.out.println("ReadHandler thread: " + Thread.currentThread());
            LOGGER.info("Читаю");
            SocketChannel socketChannel = (SocketChannel) key.channel();
            byte[] byteArray = connection.read(socketChannel);
            key.channel().register(selector, SelectionKey.OP_WRITE);
            if (byteArray == null) return null;
            int flag = serializer.checkByteArray(byteArray);

            ExecutorService service = Executors.newFixedThreadPool(max_threads);
            Command_Executer comExe = new Command_Executer(flag, serializer, byteArray);
            Future futureResponse = service.submit(comExe);
            String response = (String) futureResponse.get();

            service.shutdown();
            return response;
        } catch (NullPointerException | ClosedChannelException e) {
            System.err.println("Соединение с клиентом неожиданно (нет) потеряно");
            return null;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
