package lab;

import serializer.Serializer;
import socket_channel_connection.Connection;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class WriteHandler extends RecursiveAction {
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    String response;
    Connection connection;
    SelectionKey key;
    Selector selector;

    public WriteHandler(SelectionKey key, String response, Connection connection, Selector selector) {
        this.key = key;
        this.response = response;
        this.connection = connection;
        this.selector = selector;
    }

    @Override
    protected void compute() {
        Serializer serializer = new Serializer();
        LOGGER.info("Пишу ответочку");
        SocketChannel socketChannel = (SocketChannel) key.channel();

        connection.write(serializer.toByteArray(response), socketChannel);
        LOGGER.info("Отправлен ответ: " + response);
        try {
            key.channel().register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }
}
