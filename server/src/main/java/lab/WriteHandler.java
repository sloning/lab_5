package lab;

import serializer.Serializer;
import socket_channel_connection.Connection;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class WriteHandler {
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    String response;
    Connection connection;
    SelectionKey key;

    public WriteHandler(SelectionKey key, String response, Connection connection) {
        this.key = key;
        this.response = response;
        this.connection = connection;
    }

    public void call() {
        Serializer serializer = new Serializer();
        LOGGER.info("Пишу ответочку");
        SocketChannel socketChannel = (SocketChannel) key.channel();

        connection.write(serializer.toByteArray(response), socketChannel);
        LOGGER.info("Отправлен ответ: " + response);
    }
}
