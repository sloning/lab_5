package data;

import java.io.Serializable;

public class UserShell implements Serializable {
    private String login = null;
    private String password = null;
    private boolean move; //если false, то регистрация. Если true, то авторизация

    public UserShell(String login, String password, boolean move) {
        this.login = login;
        this.password = password;
        this.move = move;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isMove() {
        return move;
    }
}
