package singleplayer;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import singleplayer.objects.LongValue;
import singleplayer.objects.Player;
import singleplayer.objects.sprites.BallSprite;
import singleplayer.objects.sprites.SliderSprite;
import singleplayer.objects.sprites.Sprite;

import java.util.ArrayList;

public class SinglePlayerGameScene {

    Player player1;
    Player player2;


    public void start(Scene scene, Stage stage) {
        player1 = new Player();
        player2 = new Player();

        Group root = new Group();
        scene.setRoot(root);

        Canvas canvas = new Canvas(800, 800);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            if (!input.contains(code) )
                input.add( code );
        });

        scene.setOnKeyReleased(event -> {
            String code = event.getCode().toString();
            input.remove( code );
        });


        Sprite background = new Sprite();
        background.setImage("background.jpg");
        background.setPosition(1,1);

        SliderSprite bar1 = new SliderSprite();
        bar1.setImage("bar.png");
        bar1.setPosition(100, 760);

        SliderSprite bar2 = new SliderSprite();
        bar2.setImage("bar.png");
        bar2.setPosition(100, 40);

        BallSprite ball = new BallSprite();
        ball.updatePlayer(player1, player2);
        ball.setImage("pokeball.png");
        ball.setPosition(400, 400);
        ball.randomizeVelocity(true);

        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                ball.updatePlayer(player1, player2);
                // game logic
                bar1.setVelocity(0,0);
                if (input.contains("LEFT"))
                    bar1.addVelocity(-180,0);
                if (input.contains("RIGHT"))
                    bar1.addVelocity(180,0);

                bar2.setVelocity(0,0);
                if (input.contains("A"))
                    bar2.addVelocity(-180,0);
                if (input.contains("D"))
                    bar2.addVelocity(180,0);

                bar1.update(elapsedTime);
                bar2.update(elapsedTime);
                ball.update(elapsedTime);

                if(bar1.intersects(ball)) {
                    ball.randomizeVelocity(true);
                    player2.addScore();
                } else if(bar2.intersects(ball)) {
                    ball.randomizeVelocity(false);
                    player1.addScore();
                }

                // render
                gc.clearRect(0, 0, 800,800);
                background.render(gc);
                bar1.render(gc);
                bar2.render(gc);
                ball.render(gc);

                String pointsText = "" + player2.getScore();
                gc.fillText( pointsText, 770, 420 );
                gc.strokeText( pointsText, 770, 420 );

                String points2Text = "" + player1.getScore();
                gc.fillText( points2Text, 770, 380);
                gc.strokeText( points2Text, 770, 380);

            }
        }.start();

        stage.close();
        stage.show();
    }
}
