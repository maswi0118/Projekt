package appDirectory;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //listens for ENTER key on each of Anchor children
        for (Node i : mainPane.getChildren()){
            i.setOnKeyPressed(e -> {
                if(e.getCode() == KeyCode.ENTER){
                    if (validUser()) {
                        loadHome();
                    } else {
                        errorLabel.setVisible(true);
                        dance();
                    }
                }
            });
        }
    }
    private boolean validUser(){
        String user = loginField.getText();
        String password = passwordField.getText();

        return (user.equalsIgnoreCase("siema") && password.equalsIgnoreCase("siema"));
    }

    // loads home page upon successful login
    private void loadHome(){
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent, 1200,700);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch (IOException ex){
            System.out.println("kuzwa scena sie nie laduje");
        }
    }

    //displays nice animation of credentials mismatch error
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
}
