package FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lab.LanguageController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FXMLResultController {
    public Label resultLabel;
    public Button resultButton;

    public static Date FormattingDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.parse(str);
    }

    @FXML
    public void initialize() {
        changeLangOfWindow();
    }


    public void resultButtonClose() {
        Stage stage = (Stage) resultButton.getScene().getWindow();
        stage.close();
    }

    //TODO сделать перевод инфо
    private void changeLangOfWindow() {
        resultButton.setText(LanguageController.loadLocale("helpCloseButton"));
    }

    public void setResultLabel(String text) throws ParseException {
        String regex = "Тип коллекции: LinkedHashMap\n" +
                "Количество элементов коллекции: (\\d+)\n" +
                "Дата создания колекции: *";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        matcher.find();

        int moviesInCollection = Integer.parseInt(matcher.group(1));
//        Date dateOfCreation = FormattingDate(matcher.group(2));
        Date dateOfCreation = new Date();
        resultLabel.setText(LanguageController.resultInfo(moviesInCollection, dateOfCreation));
    }
}
