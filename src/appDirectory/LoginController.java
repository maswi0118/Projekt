package appDirectory;

import javafx.animation.*;
import javafx.beans.value.WritableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LoginController implements Initializable {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button signInButton;
    @FXML
    private Label errorLabel;
    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //listens for ENTER key on each of Anchor children
        for (Node i : mainPane.getChildren()){
            i.setOnKeyPressed(e -> {
                //vets for respective key stroke: enter or arrows
                manageKeyInput(e, i);
            });
        }
    }
    private boolean validUser(){
        String user = loginField.getText();
        String password = passwordField.getText();

        return (user.equalsIgnoreCase("siema") && password.equalsIgnoreCase("siema"));
    }

    // loads home page upon successful login
    private void loadHome() throws IOException {
        try{
            Stage stage = (Stage) mainPane.getScene().getWindow();
            Parent homescene = FXMLLoader.load(getClass().getResource("HomePage.fxml"));

            homescene.translateYProperty().set(mainPane.getScene().getHeight());

            stackPane.getChildren().add(homescene);
            //load new scene from btm to top

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(homescene.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(400), kv);
            timeline.getKeyFrames().add(kf);


            //After completing animation, remove first scene
            timeline.setOnFinished(t -> {
                rescaleStage(stage);
                stackPane.getChildren().remove(mainPane);
            });
            timeline.play();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //displays a nice animation of credentials mismatch error
    private void dance(){
       FadeTransition errorLabelAnim = new FadeTransition(Duration.millis(2000), errorLabel);
        errorLabel.setVisible(true);
        errorLabel.setOpacity(0);
        errorLabelAnim.setFromValue(0);
        errorLabelAnim.setToValue(1);
        errorLabelAnim.setCycleCount(2);
        errorLabelAnim.setAutoReverse(true);
        errorLabelAnim.play();
    }

    private void rescaleStage(Stage stage){
        //set all new scene children (stackpane) invisible, then invoke in an animation

        //overridden methods so as to alter stage dimensions; result corny as fuck
        //touch up if time allows (?)
        WritableValue<Double> writableHeight = new WritableValue<Double>() {
            @Override
            public Double getValue() {
                return stage.getHeight();
            }

            @Override
            public void setValue(Double value) {
                stage.setHeight(value);
            }
        };
        WritableValue<Double> writableWidth = new WritableValue<Double>() {
            @Override
            public Double getValue() {
                return stage.getWidth();
            }

            @Override
            public void setValue(Double value) {
                stage.setWidth(value);
            }
        };


        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,new KeyValue(writableHeight, writableHeight.getValue()), new KeyValue(writableWidth, writableWidth.getValue())),
                new KeyFrame(Duration.millis(550), new KeyValue(writableHeight, stage.getScene().getHeight()), new KeyValue(writableWidth, stage.getScene().getWidth()))
        );
        timeline.play();
    }

    //generates arrow key array; literally useless yet will not be discarded of until code cleanup on 19th
    private ArrayList arrowKeys(){
        ArrayList arrowArray = new ArrayList<KeyEvent>();

        arrowArray.addAll(Arrays.asList(KeyCode.UP, KeyCode.DOWN));
        return arrowArray;
    }

    //key manipulation method: loading home page or switching between text fields
    private void manageKeyInput(KeyEvent e, Node i) {
        //ENTER -> new scene
        if (e.getCode() == KeyCode.ENTER) {
            if (validUser()) {
                try {
                    loadHome();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } else {
                errorLabel.setVisible(true);
                dance();
                passwordField.clear();
                loginField.clear();
            }
        }
        //UP or DOWN -> text field switching
        if (arrowKeys().contains(e.getCode())) {
            if (e.getCode() == KeyCode.UP && i == loginField) {
                passwordField.requestFocus();
            } else if (e.getCode() == KeyCode.DOWN && i == passwordField) {
                loginField.requestFocus();
            } else if (e.getCode() == KeyCode.UP && i == passwordField) {
                loginField.requestFocus();
            } else {
                passwordField.requestFocus();
            }
        }
    }
}
