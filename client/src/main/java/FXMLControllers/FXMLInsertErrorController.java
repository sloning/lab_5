package FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lab.LanguageController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class FXMLInsertErrorController {
    public Label insertErrorLabel;
    public Button insertErrorButton;

    @FXML
    public void initialize() {
        changeLangOfWindow();
    }

    private void changeLangOfWindow() {
        insertErrorLabel.setText(LanguageController.loadLocale("insertErrorLabel"));
        insertErrorButton.setText(LanguageController.loadLocale("helpCloseButton"));
    }

    public void resultButtonClose() {
        Stage stage = (Stage) insertErrorButton.getScene().getWindow();
        stage.close();
    }

    // Перевод может перезаписывать текст, записанный этой функцией
    public void setResultLabel(String text) throws UnsupportedEncodingException {
        insertErrorLabel.setText(new String(text.getBytes("Windows-1251"), StandardCharsets.UTF_8));
    }
}
