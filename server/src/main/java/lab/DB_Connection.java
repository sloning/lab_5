package lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DB_Connection {
    //TODO доступ к бд нужен только командам. Как передать его им?
    static Logger LOGGER = Logger.getLogger(DB_Connection.class.getName());
    String db_url;
    String db_user;
    String db_pass;
    Connection db_connection = null;

    public DB_Connection(String db_url, String db_user, String db_pass) {
        this.db_url = db_url;
        this.db_user = db_user;
        this.db_pass = db_pass;
        initial_connect();
    }

    private void initial_connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.info("PostgreSQL JDBC Driver не найден");
            e.printStackTrace();
            return;
        }

        LOGGER.info("PostgreSQL JDBC Driver успешно подключён");

        try {
            db_connection = DriverManager.getConnection(db_url, db_user, db_pass);
        } catch (SQLException e) {
            LOGGER.info("Не удалось установить соединение с БД");
            e.printStackTrace();
            return;
        }

        if (db_connection != null) {
            LOGGER.info("Подключение к БД выполнено успешно");
        } else {
            LOGGER.info("Не удалось установить соединение с БД");
        }
    }

    public void close() throws SQLException {
        db_connection.close();
    }
}
