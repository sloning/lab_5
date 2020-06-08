package FXMLControllers;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class Ad1Controller {
    public Button adGOButton;
    public Button adCloseButton;

    public void adGO() throws IOException {
        Desktop.getDesktop().browse(URI.create("https://1x-bet61864.com/"));
        Stage stage = (Stage) adGOButton.getScene().getWindow();
        stage.close();
    }

    public void adClose() {
        Stage stage = (Stage) adCloseButton.getScene().getWindow();
        stage.close();
    }
}
