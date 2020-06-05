package lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLDocumentController {
    public Button helpCloseButton;
    public Button changeUserButton;
    public Label currentUser;
    private FXMLAuthController authController;

    //Выводит окно помощи
    public void onClickMethod(ActionEvent actionEvent) {    //TODO fix text
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Help.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("Helpful window");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();
    }

    //закрывает окно помощи
    public void onHelpCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) helpCloseButton.getScene().getWindow();
        stage.close();
    }

    //выводит окно входа/регистрации
    public void changeUser(ActionEvent actionEvent) {    //TODO сделать окно закрываемым только по кнопке
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource("/fxml/Auth.fxml"));
            root = myloader.load();
            authController = myloader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMainController();

        Stage stage = new Stage();
        stage.setTitle("Auth");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setCurrentUser(String text) {
        currentUser.setText(text);
    }

    public void setMainController() {
        authController.setMainController(this);
    }

    public void setChangeUserButton() {
        changeUserButton.setText("Switch account");
    }
}
