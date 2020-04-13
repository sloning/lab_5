package server;
import java.io.*;
import java.net.*;

public class Server {
    public  static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1010);
        try (
                Socket clientSocket = serverSocket.accept();
                //accept() ждет, пока кто-то подключится. когда это происходит, возвращает подключенный socket
                //пока какой-нибудь порт не подключится, дальнейшее выполнение не произойдет
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                //класс-адаптер, который читает символы, а не потоки
                //в отличие от BufferedWriter выталкивает содержимое буфера автоматически
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //мы можем что-либо принять с помощью in и отослать с помощью out
        ) {
            String InputLine;
            System.out.println("new connection from: " + clientSocket.getRemoteSocketAddress().toString());


        }
    }

}
