package lab;

import DB.DBWorker;
import commands.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ServerMain {
    static Logger LOGGER = Logger.getLogger(ServerMain.class.getName());

    public static void main(String[] args) throws Exception {
        initCommands();
        LOGGER.info("Сервер запущен");

//        if (args.length > 0) {
//            LoadMovies loader = new LoadMovies();
//            loader.load(args[0]);
//            LOGGER.info("Десериализация выполнена");
//        }

        DBWorker dbWorker = new DBWorker();
        Server server = new Server();
    }

    private static void initCommands() {
        Clear clear = new Clear();
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
        Show show = new Show();
        Update update_id = new Update();
    }
}