package lab;

import input_output.InputOutput;
import data.Shell;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        try (
                Socket socket = new Socket("127.0.0.1", 1010);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                ObjectOutputStream serializer = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream deserializer = new ObjectInputStream(socket.getInputStream());
        ) {
            System.out.println("Client connected to socket.");
            // проверяем живой ли канал и работаем если живой
            while(!socket.isOutputShutdown()) {
                // ждём консоли клиента на предмет появления в ней данных
                if(br.ready()) {
                    // данные появились - работаем
                    System.out.println("Client start writing in channel...");
                    Thread.sleep(1000);
                    InputOutput inputOutput = new InputOutput();
                    inputOutput.Input();
                    Shell shell = inputOutput.getShell();
                    serializer.writeObject(inputOutput.getShell());

                    // пишем данные с консоли в канал сокета для сервера
                    System.out.println("Client sent message " + ((Shell) shell).toString() + " to server.");
                    // ждём чтобы сервер успел прочесть сообщение из сокета и ответить
                    Thread.sleep(1000);
                    if(in.read() > -1) {
                        // если успел забираем ответ из канала сервера в сокете и сохраняем её в in переменную, печатаем на свою клиентскую консоль
                        System.out.println("reading...");
                        int coming = in.read();
                        System.out.println(coming);
                    }
                }
            }
        } catch (Throwable cause) {
            System.out.println("Connection error: " + cause.getMessage());
        }
    }
}