package pong.objects.nodes;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StyledText {

    private String text;
    private Text node;

    public StyledText(String text, int x, int y, Font font) {
        this.text = text;
        node = new Text(text);
        node.setY(y);
        node.setX(x - (text.length() * font.getSize() /3));
        node.setFont(font);
    }

    public Text getNode() {
        return node;
    }



}
