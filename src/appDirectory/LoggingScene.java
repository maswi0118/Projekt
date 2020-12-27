package appDirectory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoggingScene {
    Label loginLabel;
    Label repeatPasswordLabel;
    Label passwordLabel;

    TextField loginTextField = new TextField();
    PasswordField passwordField = new PasswordField();
    PasswordField repeatPasswordField = new PasswordField();

    Button setCredentials;
    Button returnButton;

    Scene loggingScene;
    Scene credentialsScene;
    Scene currentScene;

    VBox loggingLayout;
    VBox credentialsLayout;

    LoggingScene(Stage stage){

        //setting credentials button
        setCredentials = new Button();
        setCredentials.setText("sign in");
        setCredentials.setOnAction(e -> {
            System.out.println("credentials");
            loggingLayout = null;
            currentScene = credentialsScene;
            stage.setScene(setLoggingScene());
        });

        //return to main logging scene button
        returnButton = new Button("return");
        returnButton.setOnAction(e -> {
            System.out.println("return");
            credentialsLayout = null;
            currentScene = loggingScene;
            stage.setScene(setLoggingScene());
        });

        loggingLayout = new VBox(20);
        loggingLayout.setAlignment(Pos.CENTER);
        loggingLayout.getChildren().addAll(
                loginLabel = new LabelBuilder().makePretty("Arial", 15, 30, 30),
                loginTextField,
                passwordLabel = new LabelBuilder().makePretty("Arial", 15, 30, 30),
                passwordField,
                setCredentials
        );

        credentialsLayout = new VBox(20);
        credentialsLayout.setAlignment(Pos.CENTER);
        credentialsLayout.getChildren().addAll(
                loginLabel = new LabelBuilder().makePretty("Arial", 15, 30, 30),
                loginTextField,
                passwordLabel = new LabelBuilder().makePretty("Arial", 15, 30, 30),
                passwordField,
                repeatPasswordLabel = new LabelBuilder().makePretty("Arial", 15, 30, 30),
                repeatPasswordField,
                returnButton
        );

        credentialsScene = new Scene(credentialsLayout, 600, 600);
        loggingScene = new Scene(loggingLayout, 600, 600);
        currentScene = credentialsScene;
    }

    public Scene setLoggingScene() {
        return currentScene;
    }
}
