package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sample.methods.commonAnim;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class startController extends commonAnim implements Initializable {
    @FXML
    ImageView initImage;
    @FXML
    Button enterButton;
    @FXML
    StackPane parentContainer;
    @FXML
    AnchorPane mainPane;
    @FXML
    TextField initText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableEnable(parentContainer);
        enterButton.setOnMousePressed(e->{
            Color bcgColor = Color.web("CORAL");
            shadowEffect(e, 0.75, bcgColor).setOnFinished(ev->{disableAll(mainPane);});
            shadowEffect(e, 0.75, bcgColor).play();

            String add = "login.fxml";
            loadSceneFrom(add, parentContainer, mainPane, "right");
        });
    }

}
