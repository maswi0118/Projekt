package appDirectory;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class commonAnim {
    public void loadPage(String page, StackPane stackPane){
        try{
            Parent sampleHome = FXMLLoader.load(getClass().getResource((String)page));

            sampleHome.translateYProperty().set(stackPane.getScene().getHeight());

            stackPane.getChildren().add(sampleHome);
            //load transition scene from btm to top

            FadeTransition fd = new FadeTransition(Duration.millis(1000),stackPane);
            fd.setFromValue(0);
            fd.setToValue(1);
             fd.play();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
