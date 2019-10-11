package singleplayer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pong.multiplayer.Player;
import singleplayer.objects.nodes.StyledButton;
import singleplayer.objects.nodes.StyledText;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class MainMenuScene {

    public void start(Stage theStage, Scene scene) {
        Pane root = new Pane();

        Font titleFont = Font.font("Helvetica", FontWeight.BOLD, 75);
        Text text = new StyledText("CMH PONG", 400, 150, titleFont).getNode();
        text.setFill(Color.BLUE);
        text.setStroke(Color.BLACK);

        Font buttonFont = Font.font("Helvetica", FontWeight.BOLD, 27);
        Button singlePlayerButton = new StyledButton("Singleplayer", 400, 300, buttonFont).setAction(event -> {
            SceneController.getInstance().changeToSinglePlayerGame();
        }).getNode();
        singlePlayerButton.setTextFill(Color.GREEN);

        // new Image(url)
        Image image = new Image("background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        root.setBackground(background);
        root.getChildren().add(text);
        root.getChildren().add(singlePlayerButton);

        scene.setRoot(root);
        theStage.show();
    }
}
