package appDirectory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoggingScene {
    Button siema;
    Scene loggingScene;

    LoggingScene(){
        siema = new Button();
        siema.setText("siema, press me");
        siema.setOnAction(e -> System.out.println("siema"));


        VBox loggingLayout = new VBox();
        loggingLayout.setAlignment(Pos.CENTER);
        loggingLayout.getChildren().add(siema);

        loggingScene = new Scene(loggingLayout, 600, 600);
    }

    public Scene setLoggingScene() {
        return loggingScene;
    }
}
