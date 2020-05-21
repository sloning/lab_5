package DB;

import data.AnswerUserShell;
import data.UserShell;
import lab.Server;

import java.sql.*;
import java.util.logging.Logger;

public class Login {
    static Logger LOGGER = Logger.getLogger(Server.class.getName());
    private UserShell userShell;
    private String info = "ничего";

    public Login(UserShell userShell){
        this.userShell = userShell;
    }

    public void authorisation() {
        try {
            Statement statement = DBWorker.getConnection().createStatement();
            if (userShell.isMove()) {
                //авторизация пользователя
                ResultSet resultSet = statement.executeQuery("select * from users where username = '" + userShell.getLogin() + "'");
                if (resultSet.wasNull()) {
                    this.info = "Данного пользователя нет в базе данных. Повторите попытку";
                    LOGGER.info("Авторизация не удалась");
                    resultSet.close();
                } else {
                    while (resultSet.next()) {
                        String result = resultSet.getString("password");
                        if (userShell.getPassword().equals(result)) {
                            this.info = "Авторизация прошла успешно";
                            LOGGER.info("Пользователь прошел авторизацию");
                            resultSet.close();
                        } else {
                            this.info = "Неверный пароль. Повторите попытку";
                            LOGGER.info("Авторизация не удалась");
                            resultSet.close();
                        }
                    }
                }
            } else {
                //добавление нового пользователя в базу данных
                statement.executeUpdate("insert into users(username, password) values ('" + userShell.getLogin() + "', '" + userShell.getPassword() + "')");
                LOGGER.info("добавлен новый пользователь" + userShell.getLogin());
                info = "Регистрация пользователя прошла успешно"; //TODO допилить фичу если пользователь с таким именем уже существет
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getInfo(){
        return info;
    }
}
