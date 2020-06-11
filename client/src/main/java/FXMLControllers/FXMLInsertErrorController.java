package FXMLControllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class FXMLInsertErrorController {
    public Label resultLabel;
    public Button resultButton;

    public void resultButtonClose() {
        Stage stage = (Stage) resultButton.getScene().getWindow();
        stage.close();
    }

    public void setResultLabel(String text) throws UnsupportedEncodingException {
        resultLabel.setText(new String(text.getBytes("Windows-1251"), StandardCharsets.UTF_8));
    }
}
