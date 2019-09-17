package pong.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pong.multiplayer.Game;
import pong.objects.nodes.StyledButton;
import pong.objects.nodes.StyledText;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Pong extends Application {

    public Socket socket;

    public static int MAX_Y = 790;
    public static int MAX_X = 790;

    public static ArrayList<Game> games = new ArrayList<>();

    public static ScheduledExecutorService mainServices = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Pong");
        Pane root = new Pane();
        Scene theScene = new Scene(root, 800,800);
        theStage.setScene(theScene);

        Font titleFont = Font.font("Helvetica", FontWeight.BOLD, 75);
        Text text = new StyledText("CMH PONG", 400, 150, titleFont).getNode();
        text.setFill(Color.BLUE);
        text.setStroke(Color.BLACK);

        Font buttonFont = Font.font("Helvetica", FontWeight.BOLD, 27);
        Button singlePlayerButton = new StyledButton("Singleplayer", 400, 300, buttonFont).setAction(event -> {
            new MainGame().start(theScene, theStage);
        }).getNode();
        singlePlayerButton.setTextFill(Color.GREEN);

        Button multiplayerButton = new StyledButton("Multiplayer ", 400, 400, buttonFont).setAction(event -> {
            new ServerSelectionScene().start(theScene, theStage);
        }).getNode();
        multiplayerButton.setTextFill(Color.GREEN);

        // new Image(url)
        Image image = new Image("background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        root.setBackground(background);
        root.getChildren().add(text);
        root.getChildren().add(multiplayerButton);
        root.getChildren().add(singlePlayerButton);

        theStage.show();
    }


}
