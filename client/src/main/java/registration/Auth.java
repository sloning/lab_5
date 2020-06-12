package registration;

import data.UserShell;
import lab.Client;
import lab.LanguageController;
import serializer.Serializer;
import socket_connection.Connection;

import java.net.Socket;

public class Auth {     //TODO запретить просмотр коллекции до авторизации??
    public static String login = "";
    public static String password;

    Serializer serializer = new Serializer();
    Connection connection = new Connection();
    Socket socket = Client.socket;

    public String singInUser(String user, String password) {
        login = user;
        Auth.password = password;
        String hash = Hash.encryptThisString(password);
        UserShell userShell = new UserShell(user, hash, true);
        connection.write(serializer.toByteArray(userShell), socket);
        byte[] inputBytes;
        inputBytes = connection.read(socket);
        String response = serializer.fromByteArray(inputBytes, String.class);
        return translateResponse(response);
    }

    public String registerUser(String user, String password) {
        login = user;
        Auth.password = password;
        String hash = Hash.encryptThisString(password);
        UserShell userShell = new UserShell(user, hash, false);
        connection.write(serializer.toByteArray(userShell), socket);
        byte[] inputBytes;
        inputBytes = connection.read(socket);
        String response = serializer.fromByteArray(inputBytes, String.class);
        return translateResponse(response);
    }

    private String translateResponse(String response) {
        switch (response) {
            case "Данного пользователя нет в базе данных. Повторите попытку":
                return LanguageController.loadLocale("upErrorLabel");
            case "Авторизация прошла успешно":
                return "Successful singed in";
            case "Неверный пароль. Повторите попытку":
                return LanguageController.loadLocale("inErrorLabel");
            case "Регистрация пользователя прошла успешно":
                return "Successful singed up";
        }
        return "Error occurred";
    }
}
