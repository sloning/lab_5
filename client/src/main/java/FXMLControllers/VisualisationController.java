package FXMLControllers;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import movie.Movie;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class VisualisationController implements IController {
    private final HashMap<String, Color> userColors = new HashMap<String, Color>();
    public AnchorPane rootPane;
    private FXMLDocumentController mainController;
    private List<Movie> movieList;

    public void showVisual() {
        rootPane.getChildren().clear();
        Color[] mColors = {
                Color.BLUE, // light blue
                Color.DARKBLUE, // dark blue
                Color.RED, // red
                Color.ORANGE, // orange
                Color.LAVENDER, // lavender
                Color.PURPLE, // purple
                Color.AQUA, // aqua
                Color.GREEN, // green
                Color.DARKGREY, // dark gray
                Color.PINK, // pink
                Color.LIGHTGREY  // light gray
        };

        for (Movie movie : movieList) {
            // Randomly select a fact
            Random randomGenerator = new Random(); // Construct a new Random number generator
            int randomNumber1 = randomGenerator.nextInt(mColors.length);
//            int randomNumber2 = randomGenerator.nextInt(mColors.length);

            Color colorFill = mColors[randomNumber1];
//            Color colorStroke = mColors[randomNumber2];

            Rectangle rectangle = new Rectangle(movie.getCoordinatesX() + 420, movie.getCoordinatesY() + 230, movie.getLength(), movie.getOscars());

            if (userColors.containsKey(movie.getUser())) {
                rectangle.setFill(userColors.get(movie.getUser()));
                rectangle.setStroke(userColors.get(movie.getUser()));

            } else {
                userColors.put(movie.getUser(), colorFill);
                rectangle.setFill(userColors.get(movie.getUser()));
                rectangle.setStroke(userColors.get(movie.getUser()));
            }
            FillTransition ft = new FillTransition(Duration.millis(3000), rectangle, Color.WHITE, userColors.get(movie.getUser()));
            ft.setCycleCount(1);
            ft.play();

            rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    Movie selectedMovie = movie;
                    Parent root = null;
                    FXMLUpdateController updateController = null;
                    try {
                        FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/Update.fxml")));
                        root = myloader.load();
                        updateController = myloader.getController();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    assert updateController != null;
                    updateController.setFields(selectedMovie);

                    Stage stage = new Stage();
                    stage.setTitle("Update");
                    assert root != null;
                    stage.setScene(new Scene(root, 650, 500));
                    stage.show();
                }
            });

            Label label = new Label(movie.getName());

            Ellipse ellipse = new Ellipse(movie.getDirectorLocationX() + 420, movie.getDirectorLocationY() + 230, movie.getDirectorHeight(), movie.getDirectorWeight());

            ellipse.setFill(userColors.get(movie.getUser()));
            ellipse.setStroke(userColors.get(movie.getUser()));
            FillTransition ft1 = new FillTransition(Duration.millis(3000), ellipse, Color.WHITE, userColors.get(movie.getUser()));
            ft1.setCycleCount(1);
            ft1.play();

            ellipse.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    Movie selectedMovie = movie;
                    Parent root = null;
                    FXMLUpdateController updateController = null;
                    try {
                        FXMLLoader myloader = new FXMLLoader((getClass().getResource("/fxml/Update.fxml")));
                        root = myloader.load();
                        updateController = myloader.getController();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    assert updateController != null;
                    updateController.setMainController(mainController);
                    updateController.setFields(selectedMovie);

                    Stage stage = new Stage();
                    stage.setTitle("Update");
                    assert root != null;
                    stage.setScene(new Scene(root, 650, 500));
                    stage.show();
                }
            });

            rootPane.getChildren().addAll(rectangle, ellipse);
        }
    }

    public void setMovies(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public void setMainController(FXMLDocumentController mainController) {
        this.mainController = mainController;
    }
}
