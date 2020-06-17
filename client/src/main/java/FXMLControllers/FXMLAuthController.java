package FXMLControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lab.LanguageController;
import registration.Auth;
import registration.Hash;

public class FXMLAuthController implements IController {
    public TextField inLogin;
    public PasswordField inPass;
    public TextField upLogin;
    public PasswordField upPass;
    public Label upErrorLabel;
    public Label inErrorLabel;
    public Button singInButton;
    public Button singUpButton;
    public Label inLoginLabel;
    public Label inPassLabel;
    public Label upLoginLabel;
    public Label upPassLabel;
    public Tab inTab;
    public Tab upTab;
    private FXMLDocumentController mainController;

    @FXML
    public void initialize() {
        changeLangOfWindow();
    }

    private void changeLangOfWindow() {
        singInButton.setText(LanguageController.loadLocale("singInButton"));
        singUpButton.setText(LanguageController.loadLocale("singUpButton"));
        inLoginLabel.setText(LanguageController.loadLocale("inLoginLabel"));
        inPassLabel.setText(LanguageController.loadLocale("inPassLabel"));
        upLoginLabel.setText(LanguageController.loadLocale("inLoginLabel"));
        upPassLabel.setText(LanguageController.loadLocale("inPassLabel"));
        inTab.setText(LanguageController.loadLocale("singInButton"));
        upTab.setText(LanguageController.loadLocale("singUpButton"));
    }

    public void singIn(ActionEvent actionEvent) {
        Auth auth = new Auth();
        String userName = inLogin.getText();
        String password = inPass.getText();
        String response = auth.singInUser(userName, password);

        if (response.equals("Successful singed in")) {
            Stage stage = (Stage) singInButton.getScene().getWindow();
            stage.close();
            setCurrentUser();
            setUser(userName);
            setCurrentPassword(Hash.encryptThisString(password));
            setChangeUserButton();
            enableButtons();

            mainController.useShowCommand();
        } else {
            inErrorLabel.setText(response);
        }
    }

    public void singUp(ActionEvent actionEvent) {
        Auth auth = new Auth();
        String userName = upLogin.getText();
        String password = upPass.getText();
        String response = auth.registerUser(userName, password);

        if (response.equals("Successful singed up")) {
            Stage stage = (Stage) singUpButton.getScene().getWindow();
            stage.close();
            setCurrentUser();
            setChangeUserButton();
            enableButtons();
        } else {
            upErrorLabel.setText(response);
        }
    }

    private void enableButtons() {
        mainController.enableButtons();
        mainController.disableUpdate();
    }

    private void setCurrentUser() {
        mainController.setCurrentUser(Auth.login);
    }

    private void setUser(String user) {
        mainController.setUser(user);
    }

    private void setCurrentPassword(String password) {
        mainController.setCurrentPassword(password);
    }

    public void setMainController(FXMLDocumentController mainController) {
        this.mainController = mainController;
    }

    public void setChangeUserButton() {
        mainController.setChangeUserButton();
    }
}
