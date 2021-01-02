package appDirectory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Parent root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        //Scene scene = new Scene(root);

        //primaryStage.setResizable(false);
        //primaryStage.setScene(scene);
        //primaryStage.show();

        //tak sie wywołuje to
        airQualityData.loadCurrentData(); //mozna 1000 wywolan/sekunde- ddosowanie legalne
        System.out.println(airQualityData.aqi());

        weatherData.loadCurrentData();
        System.out.println(weatherData.temperature());
        System.out.println(weatherData.feelsLike());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
