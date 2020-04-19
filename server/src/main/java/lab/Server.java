package server.src.main.java.lab;

import common.data.Shell;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        /*Clear clear = new Clear();
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
        Save save = new Save();
        Show show = new Show();
        Update_id update_id = new Update_id();

        if (args.length > 0) {
            LoadMovies loader = new LoadMovies();
            loader.load(args[0]);
        }

        while (true) {
            try {
                InputOutput inputOutput = new InputOutput();
                inputOutput.Input();
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
        }*/
        try (
                ServerSocket serverSocket = new ServerSocket(1010);
                Socket clientSocket = serverSocket.accept();
                //accept() ждет, пока кто-то подключится. когда это происходит, возвращает подключенный socket
                //пока какой-нибудь порт не подключится, дальнейшее выполнение не произойдет
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                //класс-адаптер, который читает символы, а не потоки
                //в отличие от BufferedWriter выталкивает содержимое буфера автоматически
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //мы можем что-либо принять с помощью in и отослать с помощью out

                ObjectOutputStream serializer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream deserializer = new ObjectInputStream(clientSocket.getInputStream());

        ) {
            System.out.println("new connection from: " + clientSocket.getRemoteSocketAddress().toString());
            // начинаем диалог с подключенным клиентом в цикле, пока сокет не закрыт
            while(!clientSocket.isClosed()) {
                System.out.println("Server reading from channel");
                Shell shell = (Shell) deserializer.readObject();
                // после получения данных считывает их
                System.out.println("READ from client message - " + shell.toString());
                // если условие окончания работы не
                // верно - продолжаем работу - отправляем эхо-ответ  обратно клиенту
                System.out.println("Server Wrote message to client.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}