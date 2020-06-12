package FXMLControllers;

import deserialize.LoadMovies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lab.Client;
import lab.LanguageController;
import movie.Movie;
import movie.MpaaRating;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class FXMLDocumentController {
    @FXML
    public Button changeUserButton;
    public Label currentUser;
    public String user;
    public String currentPassword;
    public TableColumn<Movie, String> movieCol;
    public TableColumn<Movie, Long> IDCol;
    public TableColumn<Movie, Integer> X1Col;
    public TableColumn<Movie, Float> Y1Col;
    public TableColumn<Movie, String> dateCol;
    public TableColumn<Movie, Integer> coutnCol;
    public TableColumn<Movie, Integer> lengthCol;
    public TableColumn<Movie, String> genreCol;
    public TableColumn<Movie, MpaaRating> ratingCol;
    public TableColumn<Movie, String> direcotrCol;
    public TableColumn<Movie, String> birthDayCol;
    public TableColumn<Movie, Double> heightCol;
    public TableColumn<Movie, Float> weightCol;
    public TableColumn<Movie, String> locCol;
    public TableColumn<Movie, Integer> X2Col;
    public TableColumn<Movie, Long> Y2Col;
    public TableColumn<Movie, Integer> Z2Col;
    public TableColumn<Movie, String> userCol;
    public TableColumn<Movie, String> keyCol;
    public TableView<Movie> mainTable;
    public ComboBox<String> langBox;
    public Button historyCommand;
    public Button helpButton;
    public Button updateCommand;
    public Button removeCommand;
    public Button minByIDCommand;
    public Label currUserLabel;
    private FXMLAuthController authController;
    private FXMLInsertController insertController;

    @FXML
    private Button showCommand;

    @FXML
    private Button clearCommand;

    @FXML
    private Button infoCommand;

    @FXML
    private Button insertCommand;

    LanguageController lc = new LanguageController();

    @FXML
    public void initialize() {
        langBox.getItems().removeAll(langBox.getItems());
        langBox.getItems().addAll("Russian", "German", "Albanian", "English");
        historyCommand.setDisable(true);
        updateCommand.setDisable(true);
        removeCommand.setDisable(true);
        minByIDCommand.setDisable(true);
        showCommand.setDisable(true);
        clearCommand.setDisable(true);
        infoCommand.setDisable(true);
        insertCommand.setDisable(true);
        helpButton.setDisable(true);
    }

    //Выводит окно помощи
    public void onClickMethod(ActionEvent actionEvent) {
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
        showAd();
        setMainController(authController);

        Stage stage = new Stage();
        stage.setTitle("Auth");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.initOwner(changeUserButton.getScene().getWindow());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    public void useShowCommand() {
        Client client = new Client();
        String response = client.clientOneCommand("show", currentUser.getText(), currentPassword);
        movieCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("Name"));
        IDCol.setCellValueFactory(new PropertyValueFactory<Movie, Long>("Id"));
        X1Col.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("CoordinatesX"));
        Y1Col.setCellValueFactory(new PropertyValueFactory<Movie, Float>("CoordinatesY"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("LocaleDate"));
        coutnCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("Oscars"));
        lengthCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("Length"));
        genreCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("Genre"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<Movie, MpaaRating>("MpaaRating"));
        direcotrCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("DirectorName"));
        birthDayCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("LocaleBirthday"));
        heightCol.setCellValueFactory(new PropertyValueFactory<Movie, Double>("DirectorHeight"));
        weightCol.setCellValueFactory(new PropertyValueFactory<Movie, Float>("DirectorWeight"));
        locCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("DirectorLocationName"));
        X2Col.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("DirectorLocationX"));
        Y2Col.setCellValueFactory(new PropertyValueFactory<Movie, Long>("DirectorLocationY"));
        Z2Col.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("DirectorLocationZ"));
        userCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("user"));
        keyCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("MovieKey"));

        mainTable.getItems().clear();
        LoadMovies loadMovies = new LoadMovies();
        List<Movie> movieList = null;
        try {
            movieList = loadMovies.load(response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Movie movie : movieList) {
            movie.setLocale(lc.getLocale());
            mainTable.getItems().add(movie);
        }
    }

    public void useInsertCommand() {
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/Insert.fxml")));
            root = myloader.load();
            insertController = myloader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMainController(insertController);

        Stage stage = new Stage();
        stage.setTitle("Insert");
        assert root != null;
        stage.setScene(new Scene(root, 650, 500));
        stage.show();


    }

    public void useHistoryCommand() {
        Client client = new Client();
        String response = client.clientOneCommand("history", currentUser.getText(), currentPassword);
        resultMethod(response);
    }

    public void useInfoCommand() {
        Client client = new Client();
        String response = client.clientOneCommand("info", currentUser.getText(), currentPassword);
        resultMethod(response);
    }

    public void resultMethod(String response) {
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/Result.fxml")));
            root = myloader.load();
            FXMLResultController resultController = myloader.getController();
            resultController.setResultLabel(response);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        showAd1();

        Stage stage = new Stage();
        stage.setTitle("Result");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showAd() {
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource("/fxml/ad.fxml"));
            Parent adRoot = myloader.load();
            Stage stage = new Stage();
            stage.setTitle("Leon.ru");
            assert adRoot != null;
            stage.setScene(new Scene(adRoot));
            stage.initOwner(changeUserButton.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAd1() {
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource("/fxml/ad1.fxml"));
            Parent adRoot = myloader.load();
            Stage stage = new Stage();
            stage.setTitle("1xBet");
            assert adRoot != null;
            stage.setScene(new Scene(adRoot));
            stage.initOwner(changeUserButton.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage(ActionEvent actionEvent) {
        lc.setLang(langBox.getValue());
        changeLangOfWindow();
    }

    private void changeLangOfWindow() {
        changeUserButton.setText(LanguageController.loadLocale("changeUserButton"));
        currUserLabel.setText(LanguageController.loadLocale("currUserLabel"));
        movieCol.setText(LanguageController.loadLocale("movieCol"));
        dateCol.setText(LanguageController.loadLocale("dateCol"));
        coutnCol.setText(LanguageController.loadLocale("coutnCol"));
        lengthCol.setText(LanguageController.loadLocale("lengthCol"));
        genreCol.setText(LanguageController.loadLocale("genreCol"));
        ratingCol.setText(LanguageController.loadLocale("ratingCol"));
        direcotrCol.setText(LanguageController.loadLocale("direcotrCol"));
        birthDayCol.setText(LanguageController.loadLocale("birthDayCol"));
        heightCol.setText(LanguageController.loadLocale("heightCol"));
        weightCol.setText(LanguageController.loadLocale("weightCol"));
        locCol.setText(LanguageController.loadLocale("locCol"));
        userCol.setText(LanguageController.loadLocale("userCol"));
        keyCol.setText(LanguageController.loadLocale("keyCol"));
        showCommand.setText(LanguageController.loadLocale("showCommand"));
        clearCommand.setText(LanguageController.loadLocale("clearCommand"));
        infoCommand.setText(LanguageController.loadLocale("infoCommand"));
        insertCommand.setText(LanguageController.loadLocale("insertCommand"));
        historyCommand.setText(LanguageController.loadLocale("historyCommand"));
        helpButton.setText(LanguageController.loadLocale("helpButton"));
        updateCommand.setText(LanguageController.loadLocale("updateCommand"));
        removeCommand.setText(LanguageController.loadLocale("removeCommand"));
        minByIDCommand.setText(LanguageController.loadLocale("minByIDCommand"));
    }

    public void setCurrentUser(String text) {
        currentUser.setText(text);
    }

    public void setCurrentPassword(String password) {
        currentPassword = password;
    }

    public <T extends IController> void setMainController(T controller) {
        controller.setMainController(this);
    }

    public void setChangeUserButton() {
        changeUserButton.setText(LanguageController.loadLocale("changeUserButton"));
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return currentUser.getText();
    }

    public String getPassword() {
        return currentPassword;
    }

    public void enableButtons() {
        historyCommand.setDisable(false);
        updateCommand.setDisable(false);
        removeCommand.setDisable(false);
        minByIDCommand.setDisable(false);
        showCommand.setDisable(false);
        clearCommand.setDisable(false);
        infoCommand.setDisable(false);
        insertCommand.setDisable(false);
        helpButton.setDisable(false);
    }
}
