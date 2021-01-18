package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.methods.airQualityData;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/startup.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        airQualityData a = new airQualityData("Cracow");
        System.out.println(a.getData().get("aqi"));
        System.out.println(a.getData().get("co"));
        System.out.println(a.getData().get("no2"));
        System.out.println(a.getData().get("o3"));
        System.out.println(a.getData().get("pm10"));
        System.out.println(a.getData().get("pm25"));
        System.out.println(a.getData().get("so2"));

//        DB db = new DB();
//        db.logger("siema", "siema");
//        db.register("mateusz2115000000000", "siema", "siema", "Warszawa");
//        db.logger("mateusz211500", "siema");
//        db.close();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
