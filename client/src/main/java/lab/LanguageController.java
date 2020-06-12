package lab;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {
    private static Locale locale = new Locale("en", "IE");

    public static String loadLocale(String element) {
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        String val = bundle.getString(element);
        return new String(val.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public static String resultInfo(int moviesInCollection, Date dateOfCreation) {
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        String info1 = bundle.getString("info");
        String info2 = bundle.getString("info2") + moviesInCollection;
        String info3 = bundle.getString("info3") + dateOfCreation;
        return info1 + "\n" + info2 + "\n" + info3;
    }

    public void setLang(String lang) {
        switch (lang) {
            case "Russian":
                locale = new Locale("ru");
                break;
            case "German":
                locale = new Locale("de");
                break;
            case "Albanian":
                locale = new Locale("sq");
                break;
            case "English":
                locale = new Locale("en", "IE");
                break;
        }
    }

    public Locale getLocale() {
        return locale;
    }
}
