package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {
    private static Thread clientThread;
    public static void main(String[] args) {
        clientThread = new Thread(new Client());
        clientThread.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("neKinopoisk");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"))));
        primaryStage.show();
    }

    @Override
    public void stop() {
        clientThread.interrupt();
        System.exit(0);
    }
}
