package FXMLControllers;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class AdController {
    public Button adGOButton;
    public Button adCloseButton;

    public void adGO() throws IOException {
        Desktop.getDesktop().browse(URI.create("https://www.leon.ru/"));
        Stage stage = (Stage) adGOButton.getScene().getWindow();
        stage.close();
    }

    public void adClose() {
        Stage stage = (Stage) adCloseButton.getScene().getWindow();
        stage.close();
    }
}
