package appDirectory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

       Parent root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        //loadWeatherData.printWeather();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
