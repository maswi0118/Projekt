package appDirectory;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LabelBuilder extends Label {
    Label label;
    LabelBuilder(){
        label = new Label();
    }

    public Label makePretty(String font,
                            double size,
                            double width,
                            double height) {

        label.setFont(new Font(font, size));
        label.setMaxHeight(height);
        label.setMaxWidth(width);
        label.setTextFill(Color.LIGHTGREEN);
        return label;
    }
}
