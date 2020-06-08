package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {
    public static void main(String[] args) {
        Thread clientThread = new Thread(new Client());
        clientThread.start();   //TODO закрывать этот поток вместе с ui
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("neKinopoisk");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"))));
        primaryStage.show();

    }
}
