package deserialize;

import commands.Commands;
import data.Shell;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Deserialize {
    public void handleReadAndWrite(SelectionKey key) throws Exception {
        // create a ServerSocketChannel to read the request
        SocketChannel client = (SocketChannel) key.channel();

        // Get msg from client
        ByteBuffer data = ByteBuffer.allocate(1024);;
        client.read(data);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        // Parse data from buffer to String
        Shell shell = (Shell) objectInputStream.readObject();
        Commands useCommands = new Commands(shell.getName(), shell.getParameter(), shell.getMovie());
        String response = useCommands.execute();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        client.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
    }

}
