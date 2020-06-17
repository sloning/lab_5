package FXMLControllers;

import data.FileCheck;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movie.Coordinates;
import movie.Location;
import movie.Movie;
import movie.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static deserialize.LoadMovies.FormattingDate;

public class FXMLScriptController implements IController {
    static List fileNames = new ArrayList<String>();
    @FXML
    public Button scriptButton;
    public TextField scriptField;
    @FXML
    private Button Cancel;
    private FXMLDocumentController mainController;

    public void removeButtonClose() {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    public void useScriptButton() throws IOException, ParseException {
        String fileName = scriptField.getText();
        fileNames.add(fileName);
        String fileCheck = FileCheck.checkFile(fileName);
        if (!fileCheck.equals("0")) {
            mainController.resultMethod("script", "Error with file :)))");
            removeButtonClose();
        }
        readFile(fileName);
    }

    private void readFile(String fileName) throws IOException, ParseException {
        FileReader fileReader = new FileReader(fileName);
        String str;
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            str = bufferedReader.readLine().replaceAll("\\s+", " ");
            if (str.isEmpty()) continue;
            executeCommand(str, bufferedReader);
        }
        bufferedReader.close();
    }

    private void executeCommand(String str, BufferedReader bufferedReader) throws IOException, ParseException {
        String key = "";
        if (Pattern.matches("insert\\s[A-Za-z0-9_]+", str)) {
            key = str.replaceAll("execute_script\\s", "");
            FXMLDocumentController.scriptKey = key;
            FXMLDocumentController.script = true;
            FXMLDocumentController.scriptMovie = readMovie(bufferedReader);
            mainController.useInsertCommand();
        } else if (Pattern.matches("execute_script\\s[A-Za-z0-9]+", str)) {
            key = str.replaceAll("execute_script\\s", "");
            if (fileNames.contains(key)) {
                return;
            }
            FXMLDocumentController.scriptKey = key;
            FXMLDocumentController.script = true;
            fileNames.add(key);
            mainController.useScript();
        } else if (Pattern.matches("update\\s[A-Za-z0-9]+", str)) {
            key = str.replaceAll("update\\s", "");
            FXMLDocumentController.scriptKey = key;
            FXMLDocumentController.script = true;
            FXMLDocumentController.scriptMovie = readMovie(bufferedReader);
            mainController.useUpdateCommand();
        } else if (Pattern.matches("remove\\s[A-Za-z0-9]+", str)) {
            key = str.replaceAll("remove\\s", "");
            FXMLDocumentController.scriptKey = key;
            FXMLDocumentController.script = true;
            mainController.useRemoveCommand();
        } else if (Pattern.matches("show", str)) {
            mainController.useShowCommand();
        } else if (Pattern.matches("history", str)) {
            mainController.useHistoryCommand();
        } else if (Pattern.matches("min_by_id", str)) {
            mainController.useMinByIDCommand();
        } else if (Pattern.matches("help", str)) {
            mainController.onClickMethod();
        } else if (Pattern.matches("clear", str)) {
            mainController.useClearCommand();
        } else if (Pattern.matches("info", str)) {
            mainController.useInfoCommand();
        }
    }

    private Movie readMovie(BufferedReader bufferedReader) throws IOException, ParseException {
        String movieName = bufferedReader.readLine();
        String movieId = bufferedReader.readLine();
        String directorName = bufferedReader.readLine();
        String movieRating = bufferedReader.readLine();
        String movieGenre = bufferedReader.readLine();
        int amountOfOscars = Integer.parseInt(bufferedReader.readLine());
        int x = Integer.parseInt(bufferedReader.readLine());
        int movieLength = Integer.parseInt(bufferedReader.readLine());
        int locX = Integer.parseInt(bufferedReader.readLine());
        int locY = Integer.parseInt(bufferedReader.readLine());
        int locZ = Integer.parseInt(bufferedReader.readLine());
        float y = Float.parseFloat(bufferedReader.readLine());
        float directorWeight = Float.parseFloat(bufferedReader.readLine());
        double directorHeight = Double.parseDouble(bufferedReader.readLine());
        Date date = FormattingDate(bufferedReader.readLine());
        Date directorDate = FormattingDate(bufferedReader.readLine());
        String movieKey = bufferedReader.readLine();
        String user = bufferedReader.readLine();
        String locName = bufferedReader.readLine();

        Coordinates movieCoordinates = new Coordinates(x, y);
        Location movieLocation = new Location(locName, locX, locY, locZ);
        Person director = new Person(directorName, directorHeight, directorWeight, movieLocation, directorDate);
        Movie movie = new Movie(movieName, movieId, date, movieCoordinates, amountOfOscars, movieLength, director);
        movie.setGenre(movieGenre);
        movie.setMpaaRating(movieRating);
        movie.setMovieKey(movieKey);
        movie.setUser(user);
        return movie;
    }

    @Override
    public void setMainController(FXMLDocumentController mainController) {
        this.mainController = mainController;
    }

    public void script(String fileName) throws IOException, ParseException {
        scriptField.setText(fileName);
        useScriptButton();
    }
}
