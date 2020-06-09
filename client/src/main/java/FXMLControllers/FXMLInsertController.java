package FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movie.Movie;

public class FXMLInsertController {

    @FXML
    private TextField Name;

    @FXML
    private TextField X;

    @FXML
    private TextField Y;

    @FXML
    private TextField oscars;

    @FXML
    private TextField length;

    @FXML
    private TextField directorName;

    @FXML
    private TextField height;

    @FXML
    private TextField weight;

    @FXML
    private TextField locationName;

    @FXML
    private TextField locX;

    @FXML
    private TextField locY;

    @FXML
    private TextField locZ;

    @FXML
    private ChoiceBox<?> Genre;

    @FXML
    private ChoiceBox<?> MPARating;

    @FXML
    private Button insert;

    @FXML
    private Button cancel;

    private Movie movie;


    public void InsertClose() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void setName() {
        movie.setName(Name.getText());
    }

    public void setX() {
        int x = Integer.parseInt(X.getText());
        movie.setCoordinates(x);
    }

    public void setY() {
        float y = Float.parseFloat(Y.getText());
    }

    public void setOscars() {
        int oscars = Integer.parseInt(this.oscars.getText());
        movie.setOscarsCount(oscars);
    }

    public void setLength() {
        int length = Integer.parseInt(this.length.getText());
        movie.setLength(length);
    }

    public void setDirectorName() {

    }
}
