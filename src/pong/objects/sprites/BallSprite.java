package pong.objects.sprites;

import javafx.scene.canvas.GraphicsContext;
import pong.game.Pong;
import pong.objects.Player;

import java.util.Random;

public class BallSprite extends Sprite {

    private Player player1;
    private Player player2;

    private int currentVelocityY;
    private int currentVelocityX;

    public void updatePlayer(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void randomizeVelocity(boolean fromBottom) {
        int multiplier;
        Random random = new Random();
        multiplier = fromBottom ? -1 : 1;
        currentVelocityY = (multiplier * 150) + Math.max((player1.getScore() + player2.getScore()) * 2, 1);
        currentVelocityX = (random.nextInt(10 + 10) - 10) * random.nextInt(20);
        this.setVelocity(currentVelocityX, currentVelocityY);
    }

    private void resetBall() {
        this.setPosition(400, 400);
        this.randomizeVelocity(true);
    }

    @Override
    public void render(GraphicsContext gc) {
        if(getPositionX() >= Pong.MAX_X - 6) {
            this.setVelocity(currentVelocityX * -1, currentVelocityY);
        } else if(getPositionX() <= 2) {
            this.setVelocity(currentVelocityX * -1, currentVelocityY);
        }
        if(getPositionY() >= Pong.MAX_Y) {
            player1.addScore();
            resetBall();
        } else if(getPositionY() <= 2) {
            player2.addScore();
            resetBall();
        }
        super.render(gc);
    }
}
