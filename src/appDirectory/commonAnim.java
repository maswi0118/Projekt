package appDirectory;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.text.IconView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class commonAnim {

    public void loadPage(String page, StackPane stackPane){
        try{
            Parent sampleHome = FXMLLoader.load(getClass().getResource((String)page));

            sampleHome.translateYProperty().set(stackPane.getScene().getHeight());

            stackPane.getChildren().add(sampleHome);

            FadeTransition fd = new FadeTransition(Duration.millis(1000),stackPane);
            fd.setFromValue(0);
            fd.setToValue(1);
            fd.play();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //for homePage to load
    public void loadBookmark(ActionEvent event){
        String newScene = switch (((Button) (event.getSource())).getId()) {
            case "logOut" -> "logOut.fxml";
            case "weatherCond" -> "weatherCond.fxml";
            case "foodValue" -> "foodValue.fxml";
            case "airCond" -> "airCond.fxml";
            default -> "";
        };
        Timeline dropShadow = shadowEffect(event, 0.75);
        dropShadow.play();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(newScene));
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Scene scene=new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error in switching stages");
        }
    }

    public Timeline shadowEffect(ActionEvent event, double radius){
        Button button=(Button) event.getSource();
        java.awt.Color orig = (Color) button.getBackground().getFills();
        Color newCol = orig.darker();

        DropShadow shadow = new DropShadow();
        shadow.setColor(awtToFxColor(newCol));
        shadow.setSpread(radius);

        Timeline shadowAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(shadow.radiusProperty(), 0d)),
                new KeyFrame(Duration.millis(150), new KeyValue(shadow.radiusProperty(), 20d)));
        shadowAnimation.setAutoReverse(true);
        shadowAnimation.setCycleCount(2);
        Node target = button;
        target.setEffect(shadow);

        return shadowAnimation;
    }

    //method to convert from awt.Color to fxColor - it is easier (?) to access background color
    public javafx.scene.paint.Color awtToFxColor(java.awt.Color awtColor){
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0;
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);

        return fxColor;
    }

}
