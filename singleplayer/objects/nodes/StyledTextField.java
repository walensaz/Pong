package singleplayer.objects.nodes;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StyledTextField {

    private TextField node;
    private Text text;

    public StyledTextField(String label, int x, int y, int height, int width, Font textFieldFont,Font labelFont) {
        node = new TextField();
        node.setTranslateY(y);
        node.setTranslateX(x);
        node.setFont(textFieldFont);
        node.setPrefWidth(width);
        node.setPrefHeight(height);
        text = new StyledText(label,(x - width + (x / 3) - 18), y + 15, labelFont).getNode();
    }

    public TextField getNode() {
        return node;
    }

    public Text getLabel() { return text; }

}
