package lab;

import FXMLControllers.FXMLDocumentController;
import javafx.application.Platform;

import static java.lang.Thread.sleep;

public class Updater implements Runnable {
    FXMLDocumentController mainController;
    String user;

    public Updater(FXMLDocumentController mainController, String user) {
        this.mainController = mainController;
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            if (user != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mainController.useShowCommand();
                    }
                });
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
