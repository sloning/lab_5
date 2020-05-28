package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBWorker {
    private final Logger LOGGER = Logger.getLogger(DBWorker.class.getName());
    private final String PASS = "1234";
    private static Connection connection;
    //private final String DB_URL = "jdbc:postgresql://localhost:5433/movie_collection";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/test";
    private final String USER = "postgres";
    //private final String PASS = "vlad";

    public DBWorker() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.info("PostgreSQL JDBC Driver не найден");
            e.printStackTrace();
            return;
        }

        LOGGER.info("PostgreSQL JDBC Driver успешно подключён");

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.info("Не удалось установить соединение с БД");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            LOGGER.info("Подключение к БД выполнено успешно");
        } else {
            LOGGER.info("Не удалось установить соединение с БД");
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
