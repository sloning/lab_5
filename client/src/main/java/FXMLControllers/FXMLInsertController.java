package FXMLControllers;

import data.Shell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab.Client;
import lab.LanguageController;
import movie.Location;
import movie.Movie;
import movie.Person;

import java.io.IOException;

public class FXMLInsertController implements IController {

    public Text insertMovieName;
    public Text insertCoordsMovie;
    public Text insertOscars;
    public Text insertLegth;
    public Text insertGenre;
    public Text insertRating;
    public Text insertKey;
    public Text insertDirectorName;
    public Text insertHieght;
    public Text insertWeight;
    public Text insertLocation;
    public Text insertsLocCoords;
    ObservableList<String> genreList = FXCollections.observableArrayList("FANTASY", "MUSICAL", "COMEDY");

    ObservableList<String> MPARatingList = FXCollections.observableArrayList("G", "PG", "PG_13");

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
    private TextField key;

    @FXML
    private TextField locY;

    @FXML
    private TextField locZ;

    @FXML
    private ComboBox genre;

    @FXML
    private ComboBox MPARating;

    @FXML
    private Button insert;

    @FXML
    private Button cancel;

    private Shell shell;

    private String user;

    private String password;

    private FXMLDocumentController mainController;

    @FXML
    public void initialize() {
        genre.setValue("FANTASY");
        genre.setItems(genreList);

        MPARating.setValue("G");
        MPARating.setItems(MPARatingList);

        changeLangOfWindow();
    }

    private void changeLangOfWindow() {
        insertMovieName.setText(LanguageController.loadLocale("insertMovieName"));
        insertCoordsMovie.setText(LanguageController.loadLocale("insertCoordsMovie"));
        insertOscars.setText(LanguageController.loadLocale("insertOscars"));
        insertLegth.setText(LanguageController.loadLocale("insertLegth"));
        insertGenre.setText(LanguageController.loadLocale("insertGenre"));
        insertRating.setText(LanguageController.loadLocale("insertRating"));
        insertKey.setText(LanguageController.loadLocale("insertKey"));
        insertDirectorName.setText(LanguageController.loadLocale("insertDirectorName"));
        insertHieght.setText(LanguageController.loadLocale("insertHieght"));
        insertWeight.setText(LanguageController.loadLocale("insertWeight"));
        insertLocation.setText(LanguageController.loadLocale("insertLocation"));
        insertsLocCoords.setText(LanguageController.loadLocale("insertsLocCoords"));
        insert.setText(LanguageController.loadLocale("insert"));
        cancel.setText(LanguageController.loadLocale("cancel"));
    }

    public void InsertClose() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void setName(Movie movie) {
        movie.setName(Name.getText());
    }

    public void setX(Movie movie) throws NumberFormatException {
        int x = Integer.parseInt(X.getText());
        movie.setCoordinates(x);
    }

    public void setY(Movie movie) throws NumberFormatException {
        float y = Float.parseFloat(Y.getText());
        movie.setCoordinates(y);
    }

    public void setOscars(Movie movie) throws NumberFormatException {
        int oscars = Integer.parseInt(this.oscars.getText());
        movie.setOscarsCount(oscars);
    }

    public void setLength(Movie movie) throws NumberFormatException {
        int length = Integer.parseInt(this.length.getText());
        movie.setLength(length);
    }

    public void setDirectorName(Person director) {
        director.setName(this.directorName.getText());
    }

    public void setDirectorHeight(Person director) throws NumberFormatException{
        double height = Double.parseDouble(this.height.getText());
        director.setHeight(height);
    }

    public void setDirectorWeight(Person director) throws NumberFormatException {
        float weight = Float.parseFloat(this.weight.getText());
        director.setWeight(weight);
    }

    public void setLocationName(Location location) {
        location.setName(locationName.getText());
    }

    public void setLocationX(Location location) throws NumberFormatException {
        int x = Integer.parseInt(locX.getText());
        location.setX(x);
    }

    public void setLocationY(Location location) throws NumberFormatException {
        long y = Long.parseLong(locY.getText());
        location.setY(y);
    }

    public void setLocationZ(Location location) throws NumberFormatException {
        int z = Integer.parseInt(locZ.getText());
        location.setZ(z);
    }

    public void setGenre(Movie movie) {
        movie.setGenre(genre.getValue().toString());
    }

    public void setMPARating(Movie movie) {
        movie.setMpaaRating(MPARating.getValue().toString());
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInsert() {
        Movie movie = new Movie();
        Person director = new Person();
        Location location = new Location();
        try {
            setName(movie);
            setX(movie);
            setY(movie);
            setOscars(movie);
            setLength(movie);
            setGenre(movie);
            setMPARating(movie);
            setDirectorName(director);
            setDirectorHeight(director);
            setDirectorWeight(director);
            setLocationName(location);
            setLocationX(location);
            setLocationY(location);
            setLocationZ(location);

            director.setLocation(location);
            movie.setDirector(director);

            shell = new Shell("insert", key.getText(), movie);
            shell.setUser(mainController.getUser());
            shell.setPassword(mainController.getPassword());

            Client client = new Client();
            String answer = client.clientOneCommandWithShell(shell);
            System.out.println(answer);

            Stage stage = (Stage) insert.getScene().getWindow();
            mainController.useShowCommand();
            stage.close();
        } catch (NumberFormatException e) {
            Parent root = null;
            try {
                FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/InsertError.fxml")));
                root = myloader.load();
                FXMLInsertErrorController errorInsertController = myloader.getController();
                errorInsertController.setResultLabel("Вы не заполнили корректно все поля");
            } catch (IOException exp) {
                exp.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setTitle("Error");
            assert root != null;
            stage.setScene(new Scene(root, 650, 500));
            stage.show();

        }
    }

    public Shell getShell() {
        return shell;
    }

    @Override
    public void setMainController(FXMLDocumentController mainController) {
        this.mainController = mainController;
    }
}
