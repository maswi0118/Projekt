package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import sample.methods.commonAnim;

public class homeController extends commonAnim implements Initializable {
    @FXML
    private StackPane homeStackPane;
    @FXML
    private ImageView weatherControl;
    @FXML
    private ImageView airControl;
    @FXML
    private ImageView foodControl;
    @FXML
    private AnchorPane buttonPane;
    @FXML
    private AnchorPane homeMainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        invokeInSequence(buttonPane).play();
        setEventListeners();
        //circularPathandRotation(buttonPane).play();

    }

    public void setEventListeners(){
        for(Node node : this.buttonPane.getChildren()){
            String direction, address;
            System.out.println(node.getId());
            switch(node.getId()){
                case "foodControl" -> {
                    address = "food.fxml";
                    direction = "up";
                }
                case "airControl" -> {
                    address = "air.fxml";
                    direction = "right";
                }
                case "weatherControl" -> {
                    address = "weather.fxml";
                    direction = "left";
                }
                default -> {
                    return;
                }
            }
            node.setOnMouseClicked(e->{
                System.out.println(address + " " + direction);
                jumpRotateReturn(node).play();
                loadSceneFrom(address, homeStackPane, homeMainPane, direction);
            });
        }
    }
}
