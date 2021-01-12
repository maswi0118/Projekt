package sample.methods;

import javafx.animation.*;
import javafx.beans.value.WritableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.beans.EventHandler;
import java.io.IOException;
import java.security.Key;
import java.util.Collections;

public class commonAnim {

    //sliding animation with defined direction
    public void loadSceneFrom(String address, StackPane stackPane, AnchorPane mainPane, String direction) {

        String fullPath = "/sample/fxmlFiles/" + address;

        try {
            Parent newScene = FXMLLoader.load(getClass().getResource(fullPath));
            stackPane.getChildren().add(newScene);
            Scene scene = stackPane.getScene();
            KeyValue kv2;

            switch (direction) {
                case "right" -> {
                    newScene.translateXProperty().set(scene.getWidth());
                    kv2 = new KeyValue(newScene.translateXProperty(), 0, Interpolator.EASE_IN);
                }
                case "left" -> {
                    newScene.translateXProperty().set(-scene.getWidth());
                    kv2 = new KeyValue(newScene.translateXProperty(), 0, Interpolator.EASE_IN);
                }
                case "up" -> {
                    newScene.translateYProperty().set(scene.getHeight());
                    kv2 = new KeyValue(newScene.translateYProperty(), 0, Interpolator.EASE_IN);
                }
                case "down" -> {
                    newScene.translateYProperty().set(-scene.getHeight());
                    kv2 = new KeyValue(newScene.translateYProperty(), 0, Interpolator.EASE_IN);
                }
                default -> {
                    return;
                }
            }

            StackPane parentContainer = (StackPane) scene.getRoot();

            Timeline timeline2 = new Timeline();
            KeyFrame kf2 = new KeyFrame(Duration.millis(300), kv2);
            timeline2.getKeyFrames().add(kf2);

            timeline2.setOnFinished(e -> {
                //clearing mainPane children list so as to disable all event listeners; other methods rendered futile
                mainPane.getChildren().clear();
                parentContainer.getChildren().remove(mainPane);
            });
            timeline2.play();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("error");

        }
    }

    public void disableEnable(StackPane stackPane) {
        SequentialTransition sq = new SequentialTransition();
        for (Node i : stackPane.getChildren()) {
            i.setOpacity(0);
            FadeTransition fd = new FadeTransition(Duration.millis(800), i);
            fd.setFromValue(0);
            fd.setToValue(1);
            sq.getChildren().add(fd);
        }
        sq.play();
    }

    public void disableAll(AnchorPane stackPane) {
        SequentialTransition sq = new SequentialTransition();

        ObservableList<Node> workingCollection = FXCollections.observableArrayList(stackPane.getChildren());
        Collections.swap(workingCollection, 0, 1);
        stackPane.getChildren().setAll(workingCollection);

        for (Node i : stackPane.getChildren()) {
            FadeTransition fd = new FadeTransition(Duration.millis(800), i);
            fd.setFromValue(1);
            fd.setToValue(0);

            sq.getChildren().add(fd);
        }
        sq.play();
    }

    public Timeline shadowEffect(MouseEvent event, double radius, Color color) {
        Button button = (Button) event.getSource();

        Color newCol = color.darker();

        DropShadow shadow = new DropShadow();
        shadow.setColor(newCol);
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

    public Timeline invokeInSequence(AnchorPane pane){
        double i = 100;
        Timeline timeline = new Timeline();

        for(Node node : pane.getChildren()){
            WritableValue<Double> writableOpacity = new WritableValue<Double>() {
                @Override
                public Double getValue() {
                    return node.getOpacity();
                }

                @Override
                public void setValue(Double opacity) {
                    node.setOpacity(opacity);
                }
            };
            WritableValue<Double> writableX = new WritableValue<Double>() {
                @Override
                public Double getValue() {
                    return node.getLayoutX();
                }

                @Override
                public void setValue(Double x) {
                    node.setOpacity(x);
                }
            };
            WritableValue<Double> writableY = new WritableValue<Double>() {
                @Override
                public Double getValue() {
                    return node.getLayoutY();
                }

                @Override
                public void setValue(Double y) {
                    node.setOpacity(y);
                }
            };

            node.setOpacity(0d);

            KeyValue kvEnd = new KeyValue(writableOpacity, 1d);
            KeyValue kvStart = new KeyValue(writableOpacity, 0d);
            KeyFrame kfStart = new KeyFrame(Duration.millis(i), kvStart);
            KeyFrame kfEnd = new KeyFrame(Duration.millis(i + 500d), kvEnd);

            timeline.getKeyFrames().addAll(kfStart, kfEnd);

            i+= 200;
        }

        return timeline;
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

    public void removeEventHandler(AnchorPane stackPane){
        for(Node i : stackPane.getChildren()){
            //System.out.println(i.getId());
            i.removeEventHandler(KeyEvent.KEY_PRESSED, i.getOnKeyPressed());
        }
    }

    public ParallelTransition circularPathandRotation(AnchorPane pane){
        ParallelTransition pt = new ParallelTransition();

        for(Node node : pane.getChildren()){
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(node);
        pathTransition.setDuration(Duration.millis(1000));
        pathTransition.setPath(new Circle(100));
        pathTransition.setCycleCount(PathTransition.INDEFINITE);

        pt.getChildren().add(pathTransition);
        }
        return pt;
    }

    public ParallelTransition jumpRotateReturn(Node node){

        ParallelTransition pt = new ParallelTransition();
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), node);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(node);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);


        switch(node.getId()) {
            case "foodControl" -> {
                translateTransition.setToY(node.getLayoutY() + 50);
            }
            case "airControl" -> {
                translateTransition.setToX(node.getLayoutX() + 50);
            }
            case "weatherControl" -> {
                translateTransition.setToX(-(node.getLayoutX() + 50));
            }
        }
        pt.getChildren().addAll(translateTransition, rotateTransition);
        return pt;
    }
}
