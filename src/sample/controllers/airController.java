package sample.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sample.methods.commonAnim;

import java.net.URL;
import java.util.ResourceBundle;

public class airController extends commonAnim implements Initializable {
    @FXML
    private StackPane airStackPane;
    @FXML
    private AnchorPane airMainPane;
    @FXML
    private JFXButton returnButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnMouseClicked(e->{
            shadowEffect(e, 0.7, Color.TOMATO.darker());
            loadSceneFrom("home.fxml", airStackPane, airMainPane, "left");
        });
    }
}
