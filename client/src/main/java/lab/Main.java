package lab;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try (
                Socket socket = new Socket("127.0.0.1", 1010);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Client connected to socket.");
            // ��������� ����� �� ����� � �������� ���� �����
            while(!socket.isOutputShutdown()) {
                // ��� ������� ������� �� ������� ��������� � ��� ������
                if(br.ready()) {
                    // ������ ��������� - ��������
                    System.out.println("Client start writing in channel...");
                    Thread.sleep(1000);
                    String clientCommand = br.readLine();
                    // ����� ������ � ������� � ����� ������ ��� �������
                    out.write(clientCommand);
                    System.out.println("Client sent message " + clientCommand + " to server.");
                    // ��� ����� ������ ����� �������� ��������� �� ������ � ��������
                    Thread.sleep(1000);
                    if(in.read() > -1) {
                        // ���� ����� �������� ����� �� ������ ������� � ������ � ��������� � � in ����������, �������� �� ���� ���������� �������
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