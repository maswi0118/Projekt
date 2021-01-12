package sample.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.methods.commonAnim;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Stack;

@SuppressWarnings({"rawtypes", "unchecked", "CollectionAddAllCanBeReplacedWithConstructor"})
public class loginController extends commonAnim implements Initializable {
    @FXML
    StackPane loginStackPane;
    @FXML
    AnchorPane loginMainPane;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button forgotButton;
    @FXML
    Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginField.requestFocus();
        disableEnable(loginStackPane);
        //listens for ENTER key on each of Anchor children
        for (Node i : loginMainPane.getChildren()){
            i.setOnKeyPressed(e -> {
                //vets for respective key stroke: enter or arrows
                manageKeyInput(e, i);
            });
        }
    }
    private void manageKeyInput(KeyEvent e, Node i) {
        //ENTER -> new scene
        if (e.getCode() == KeyCode.ENTER) {
            if (validUser()) {
                System.out.println(i.getId());
                loadSceneFrom("home.fxml", loginStackPane, loginMainPane, "up");
            } else {
                dance();
                loginField.clear();
                passwordField.clear();
                loginField.requestFocus();
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

    //generates arrow key array; literally useless yet will not be discarded of until code cleanup on 19th
    private ArrayList arrowKeys(){
        ArrayList arrowArray = new ArrayList<KeyEvent>();

        arrowArray.addAll(Arrays.asList(KeyCode.UP, KeyCode.DOWN));
        return arrowArray;
    }

    //what can be said? nothing can be
    private boolean validUser(){
        String user = loginField.getText();
        String password = passwordField.getText();

        return (user.equalsIgnoreCase("siema") && password.equalsIgnoreCase("siema"));
    }

}


