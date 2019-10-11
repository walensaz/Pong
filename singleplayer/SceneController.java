package singleplayer;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pong.game.multiplayer.MultiplayerGameScene;
import pong.game.multiplayer.ServerSelectionScene;
import pong.multiplayer.Player;

public class SceneController {

    private Scene scene;
    private Stage stage;
    private Player player;

    private static SceneController instance;

    public SceneController(Stage stage) {
        this.stage = stage;
        Pane root = new Pane();
        scene = new Scene(root, 800,800);
        stage.setScene(scene);
    }

    // static method to create instance of Singleton class
    public static SceneController getInstance()
    {
        if (instance == null)
            instance = new SceneController(null);

        return instance;
    }

    public static SceneController getInstance(Stage stage)
    {
        if (instance == null)
            instance = new SceneController(stage);

        return instance;
    }

    public void changeToMainMenuScene() {
        new MainMenuScene().start(stage, scene);
    }

    public void changeToSinglePlayerGame() {
        new SinglePlayerGameScene().start(scene, stage);
    }



}
