package FXMLControllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import registration.Auth;
import registration.Hash;

public class FXMLAuthController {
    public TextField inLogin;
    public PasswordField inPass;
    public TextField upLogin;
    public PasswordField upPass;
    public Label upErrorLabel;
    public Label inErrorLabel;
    public Button singInButton;
    public Button singUpButton;
    private FXMLDocumentController mainController;

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
        } else {
            inErrorLabel.setText(response); //TODO fix rus encoding
        }
    }

    public void singUp(ActionEvent actionEvent) {
        Auth auth = new Auth();
        String userName = upLogin.getText();
        String password = upPass.getText();
        String response = auth.registerUser(userName, password);

        if (response.equals("Successful singed up")) {
//            currentUser.setText(Auth.login);
            Stage stage = (Stage) singUpButton.getScene().getWindow();
            stage.close();
            setCurrentUser();
            setChangeUserButton();
        } else {
            upErrorLabel.setText(response); //TODO fix rus encoding
        }
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
