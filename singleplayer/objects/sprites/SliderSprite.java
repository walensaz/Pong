package singleplayer.objects.sprites;

import singleplayer.Launcher;

public class SliderSprite extends Sprite {

    @Override
    public void update(double time) {
        double tempX;
        tempX = getPositionX() + getVelocityX() * time;
        if(tempX >= Launcher.MAX_X - 5) {
            return;
        } else if(tempX <= 5) {
            return;
        }
        super.update(time);
    }
}
