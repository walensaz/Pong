package singleplayer;

import javafx.application.Application;
import javafx.stage.Stage;
import pong.multiplayer.Game;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Launcher extends Application {

    public static int MAX_Y = 790;
    public static int MAX_X = 790;

    public static ArrayList<Game> games = new ArrayList<>();

    public static ScheduledExecutorService mainServices = Executors.newScheduledThreadPool(5);

    public static String username = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        SceneController.getInstance(theStage).changeToMainMenuScene();
    }

}
