package singleplayer.objects.sprites;

import javafx.scene.canvas.GraphicsContext;

public class TextSprite {

    private String text;
    private int positionX;
    private int positionY;

    public TextSprite setText(String text) {
        this.text = text;
        return this;
    }

    public void render(GraphicsContext gc, int x, int y) {
        this.positionX = x;
        this.positionY = y;
        gc.fillText(text, x - (text.length() * 5), y);
        gc.strokeText(text, x - (text.length() * 5), y);
    }

    protected int getPositionX() {
        return positionX;
    }

    protected int getPositionY() {
        return positionY;
    }
}
