package FXMLControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab.Client;

import java.io.IOException;

public class FXMLRemoveController implements IController{

    @FXML
    private TextField RemoveField;

    @FXML
    private Button Remove;

    @FXML
    private Button Cancel;

    private FXMLDocumentController mainController;


    public void removeButtonClose() {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    public static String removeRow(String key, String user, String password) {
        Client client = new Client();
        String response = client.clientOneCommand("remove " + key, user, password);
        if (!response.equals("Фильм успешо удалён")) {
            Parent root = null;
            try {
                FXMLLoader myloader = new FXMLLoader((FXMLRemoveController.class.getResource("/fxml/InsertError.fxml")));
                root = myloader.load();
                FXMLInsertErrorController errorInsertController = myloader.getController();
                errorInsertController.setResultLabel(response);
            } catch (IOException exp) {
                exp.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setTitle("Error");
            assert root != null;
            stage.setScene(new Scene(root, 650, 500));
            stage.show();
        }
        return response;
    }

    public void removeButton() {
        String response = removeRow(RemoveField.getText(), mainController.getUser(), mainController.getPassword());
        if (response.equals("Фильм успешо удалён")) {
            Stage stage = (Stage) Remove.getScene().getWindow();
            stage.close();
            mainController.useShowCommand();
        }
    }

    @Override
    public void setMainController(FXMLDocumentController mainController) {
        this.mainController = mainController;
    }

    public void script(String key) {
        RemoveField.setText(key);
        removeButton();
    }
}
