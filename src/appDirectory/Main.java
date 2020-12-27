package appDirectory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Stage mainWindow = primaryStage;
        LoggingScene loggingScene = new LoggingScene(mainWindow);
        mainWindow.setTitle("freeweed");
        mainWindow.setScene(loggingScene.setLoggingScene());
        mainWindow.setScene();
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
