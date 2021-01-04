package appDirectory;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import javax.swing.text.IconView;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class homePageController implements Initializable {
    @FXML
    private ImageView settingsIcon;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private ImageView upperIcon;
    @FXML
    private ImageView rightIcon;
    @FXML
    private ImageView lowerIcon;
    @FXML
    private ImageView leftIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Node i : mainPane.getChildren()){
            invokeNode(i);
        }
    }


    public static void invokeNode(Node node){
        ParallelTransition parallelTransition = new ParallelTransition();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(700), node);
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(700), node);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(true);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);

        parallelTransition.getChildren().addAll(rotateTransition, scaleTransition, fadeTransition);

        parallelTransition.play();
    }
}
