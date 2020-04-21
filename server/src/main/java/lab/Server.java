package lab;

import data.Shell;
import commands.*;
import deserialize.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server {
    private static Selector selector = null;
    static Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws Exception {
        Clear clear = new Clear();
        Count_by_genre count_by_genre = new Count_by_genre();
        Execute_script execute_script = new Execute_script();
        Exit exit = new Exit();
        Filter_starts_with_name filter_starts_with_name = new Filter_starts_with_name();
        Help help = new Help();
        History history = new History();
        Info info = new Info();
        Insert_key insert_key = new Insert_key();
        Min_by_id min_by_id = new Min_by_id();
        Remove_if_lowe remove_if_lowe = new Remove_if_lowe();
        Remove_key remove_key = new Remove_key();
        Replace_if_lowe replace_if_lowe = new Replace_if_lowe();
        Show show = new Show();
        Update update_id = new Update();
        LOGGER.info("Сервер запущен");

        if (args.length > 0) {
            LoadMovies loader = new LoadMovies();
            loader.load(args[0]);
        }
        LOGGER.info("Десериализация выполнена");

        try {
            selector = Selector.open();
            // We have to set connection host, port and non-blocking mode
            ServerSocketChannel socket = ServerSocketChannel.open();
            ServerSocket serverSocket = socket.socket();
            serverSocket.bind(new InetSocketAddress("localhost", 1111));
            socket.configureBlocking(false);
            int ops = socket.validOps();
            socket.register(selector, ops, null);
            String response = null;
            LOGGER.info("Сервер готов к работёнке");

            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        // New client has been accepted
                        handleAccept(socket, key);
                    }
                    if (key.isReadable()) {
                        // We can run non-blocking operation READ on our client
                        response = handleRead(key);
                    }
                    if (key.isWritable()) {
                        handleWrite(key, response);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Соединение разорвано");
            LOGGER.warning(e.getMessage());
        }
    }

    private static void handleAccept(ServerSocketChannel mySocket, SelectionKey key) throws IOException {
        LOGGER.info("Соединение установлено");

        // Accept the connection and set non-blocking mode
        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);

        // Register that client is reading this channel
        client.register(selector, SelectionKey.OP_READ);
    }

    private static String handleRead(SelectionKey key) throws Exception {
        LOGGER.info("Читаю");
        // create a ServerSocketChannel to read the request
        SocketChannel client = (SocketChannel) key.channel();
//        // Get msg from client
        ByteBuffer data = ByteBuffer.allocate(10000000);
        client.read(data);      // TODO вот этот метод записывает в дату и прошлую команду и настоящую
        // data.mark();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        // Parse data from buffer to String
        Shell shell = (Shell) objectInputStream.readObject();
        Commands useCommands = new Commands(shell.getName(), shell.getParameter(), shell.getMovie());
        LOGGER.info("Получена команда: " + shell.getName());
        client.register(selector, SelectionKey.OP_WRITE);
        return useCommands.execute();
    }

    private static void handleWrite(SelectionKey key, String response) throws IOException {
        LOGGER.info("Пишу ответочку");
        SocketChannel client = (SocketChannel) key.channel();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        client.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));

//        ByteBuffer buffer = ByteBuffer.allocate(10000000);
//        buffer.put(response.getBytes());
//        buffer.flip();
//        client.write(buffer);

//        client.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        LOGGER.info("Отправлен ответ: " + response);
        client.register(selector, SelectionKey.OP_READ);
    }
}