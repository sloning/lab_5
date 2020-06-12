package FXMLControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lab.LanguageController;

import java.util.Locale;

public class FXMLHelpController {
    @FXML
    public Button helpCloseButton;

    LanguageController lc = new LanguageController();

    @FXML
    public void initialize() {
        changeLangOfWindow(lc.getLocale());
    }

    //закрывает окно помощи
    public void onHelpCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) helpCloseButton.getScene().getWindow();
        stage.close();
    }

    private void changeLangOfWindow(Locale locale) {
        helpCloseButton.setText(LanguageController.loadLocale("helpCloseButton"));
    }
}
