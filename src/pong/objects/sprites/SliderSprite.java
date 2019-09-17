package pong.objects.sprites;

import javafx.scene.canvas.GraphicsContext;
import pong.game.Pong;

public class SliderSprite extends Sprite {

    @Override
    public void update(double time) {
        double tempX;
        tempX = getPositionX() + getVelocityX() * time;
        if(tempX >= Pong.MAX_X - 5) {
            return;
        } else if(tempX <= 5) {
            return;
        }
        super.update(time);
    }
}
