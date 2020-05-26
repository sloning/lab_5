package DB;

import data.UserShell;
import lab.Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Login {
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    private final UserShell userShell;
    private String info = "ничего";

    public Login(UserShell userShell) {
        this.userShell = userShell;
    }

    public void login() {
        try {
            Statement statement = DBWorker.getConnection().createStatement();
            if (userShell.isMove()) {
                auth(statement);
            } else {
                registration(statement);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void auth(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from users where username = '" + userShell.getLogin() + "'");
        if (!resultSet.next()) {
            this.info = "Данного пользователя нет в базе данных. Повторите попытку";
            LOGGER.info("Авторизация не удалась");
        } else {
            do {
                String password = resultSet.getString("password");
                if (userShell.getPassword().equals(password)) {
                    this.info = "Авторизация прошла успешно";
                    LOGGER.info("Пользователь прошел авторизацию");
                } else {
                    this.info = "Неверный пароль. Повторите попытку";
                    LOGGER.info("Авторизация не удалась");
                }

            } while (resultSet.next());
        }
        resultSet.close();
    }

    private void registration(Statement statement) throws SQLException {
        statement.executeUpdate("insert into users(username, password) values ('" + userShell.getLogin() + "', '" + userShell.getPassword() + "')");
        LOGGER.info("добавлен новый пользователь" + userShell.getLogin());
        info = "Регистрация пользователя прошла успешно"; //TODO допилить фичу если пользователь с таким именем уже существет
    }

    public String getInfo() {
        return info;
    }
}
