package FXMLControllers;

import deserialize.LoadMovies;
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
import lab.Updater;
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
    public static String scriptKey;
    public Button visualizationButton;

    @FXML
    public static Movie scriptMovie;
    public static boolean script = false;
    public Button scriptButton;
    public FXMLAuthController authController;
    public FXMLInsertController insertController;
    public FXMLUpdateController updateController;
    public FXMLScriptController scriptController;
    public VisualisationController visualizationController;
    public List<Movie> movieList;
    boolean signedIn = false;
    boolean callForLangChange = false;
    private String response = "";

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
        scriptButton.setDisable(true);
        visualizationButton.setDisable(true);

        mainTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateCommand.setDisable(false);
            }
        });
    }

    //Выводит окно помощи
    public void onClickMethod() {
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
    public void changeUser() {
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
        String response1 = client.clientOneCommand("show", currentUser.getText(), currentPassword);
        if (response.equals(response1)) {
            return;
        } else {
            response = response1;
        }
        System.out.println(response);
        mainTable.setEditable(true);

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
        try {
            movieList = loadMovies.load(response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Movie movie : movieList) {
            movie.setLocale(lc.getLocale());
            mainTable.getItems().add(movie);
        }
        if (visualizationController != null) {
            visualizationController.setMovies(movieList);
            visualizationController.showVisual();
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

        if (script) {
            insertController.script(scriptMovie);
            script = false;
        }

        useShowCommand();
    }

    public void useRemoveCommand() {
        FXMLRemoveController removeController = new FXMLRemoveController();
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/Remove.fxml")));
            root = myloader.load();
            removeController = myloader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setMainController(removeController);
        Stage stage = new Stage();
        stage.setTitle("Remove");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();
        if (script) {
            removeController.script(scriptKey);
            script = false;
        }
    }

    public void useClearCommand() {
        Client client = new Client();
        String response = client.clientOneCommand("clear", currentUser.getText(), currentPassword);
        resultErrorMethod(response);

        useShowCommand();
    }

    public void useHistoryCommand() {
        Client client = new Client();
        String response = client.clientOneCommand("history", currentUser.getText(), currentPassword);
        resultMethod("history", response);
    }

    public void useInfoCommand() {
        Client client = new Client();
        String response = client.clientOneCommand("info", currentUser.getText(), currentPassword);
        resultMethod("info", response);
    }

    public void resultMethod(String cmd, String response) {
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/Result.fxml")));
            root = myloader.load();
            FXMLResultController resultController = myloader.getController();
            resultController.setResultLabel(cmd, response);
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

    public void resultErrorMethod(String response) {
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/InsertError.fxml")));
            root = myloader.load();
            FXMLInsertErrorController resultController = myloader.getController();
            resultController.setResultLabel(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showAd1();

        Stage stage = new Stage();
        stage.setTitle("Result");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void useMinByIDCommand() {
        Client client = new Client();
        String response = client.clientOneCommand("min_by_id", currentUser.getText(), currentPassword);
        resultMethod("minbyid", response);
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

    public void changeLanguage() {
        lc.setLang(langBox.getValue());
        changeLangOfWindow();
        updateCols();
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
        scriptButton.setText(LanguageController.loadLocale("scriptButton"));
        visualizationButton.setText(LanguageController.loadLocale("visualizationButton"));
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
        removeCommand.setDisable(false);
        minByIDCommand.setDisable(false);
        showCommand.setDisable(false);
        clearCommand.setDisable(false);
        infoCommand.setDisable(false);
        insertCommand.setDisable(false);
        helpButton.setDisable(false);
        scriptButton.setDisable(false);
        visualizationButton.setDisable(false);
    }

    public void useUpdateCommand() {
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/Update.fxml")));
            root = myloader.load();
            updateController = myloader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMainController(updateController);

        Movie selectedMovie = mainTable.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            disableUpdate();
            return;
        }
        updateController.setFields(selectedMovie);

        Stage stage = new Stage();
        stage.setTitle("Update");
        assert root != null;
        stage.setScene(new Scene(root, 650, 500));
        stage.show();

        if (script) {
            updateController.script(scriptMovie);
            script = false;
        }

        useShowCommand();
    }

    public void disableUpdate() {
        updateCommand.setDisable(true);
    }

    public void useScript() throws IOException, ParseException {
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/script.fxml")));
            root = myloader.load();
            scriptController = myloader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMainController(scriptController);

        Stage stage = new Stage();
        stage.setTitle("Script executor");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();

        if (script) {
            scriptController.script(scriptKey);
            script = false;
        }
    }

    public void showVisualization() {
        Parent root = null;
        try {
            FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/Visualisation.fxml")));
            root = myloader.load();
            visualizationController = myloader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMainController(visualizationController);
        visualizationController.setMovies(movieList);
        visualizationController.showVisual();

        Stage stage = new Stage();
        stage.setTitle("Visualization");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void startUpdaterThread() {
        if (!signedIn) {
            new Thread(new Updater(this, user)).start();
            signedIn = true;
        }
    }

    public void updateCols() {
        mainTable.getItems().clear();
        for (Movie movie : movieList) {
            movie.setLocale(lc.getLocale());
            mainTable.getItems().add(movie);
        }
    }
}
