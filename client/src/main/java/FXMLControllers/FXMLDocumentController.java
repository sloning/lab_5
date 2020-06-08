package FXMLControllers;

import client_controller.Controller;
import client_controller.Validation;
import input_output.InputOutput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FXMLDocumentController {
    @FXML
    public Button helpCloseButton;
    @FXML
    public Button changeUserButton;
    public Label currentUser;
    public String user;
    public String currentPassword;
    @FXML
    private FXMLAuthController authController;
    @FXML
    public TextField commandField;
    @FXML
    public Label resultText;

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

    //Ввод команды
    public void useCommand(ActionEvent actionEvent) {
        Client client = new Client();
        String response = client.clientOneCommand(commandField.getText(), currentUser.getText(), currentPassword);
        System.out.println(response);
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource("/fxml/Result.fxml"));
            root = myloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        resultText = new Label(response);

        Stage stage = (Stage) commandField.getScene().getWindow();
        stage.setTitle("Result");
        if (root == null) throw new AssertionError();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void setCurrentUser(String text) {
        currentUser.setText(text);
    }

    public void setCurrentPassword(String password) {
        currentPassword = password;
    }

    public void setMainController() {
        authController.setMainController(this);
    }

    public void setChangeUserButton() {
        changeUserButton.setText("Switch account");
    }

    public void setUser(String user) {
        this.user = user;

    }
}
