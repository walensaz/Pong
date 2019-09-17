package pong.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pong.multiplayer.Connection;
import pong.multiplayer.Game;
import pong.multiplayer.Player;
import pong.objects.nodes.StyledText;
import server.Launch;

import java.util.Arrays;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ServerSelectionScene {

    public void start(Scene scene, Stage stage) {
        Pane root = new Pane();
        scene.setRoot(root);

        Player player = new Player(5555);
        player.setFuture(Pong.mainServices.scheduleAtFixedRate(player::update, 10L, 30L, TimeUnit.MILLISECONDS));
        player.sendPacket("100:noname");

        Font titleFont = Font.font("Helvetica", FontWeight.BOLD, 75);
        Text text = new StyledText("CMH PONG", 400, 150, titleFont).getNode();
        text.setFill(Color.BLUE);
        text.setStroke(Color.BLACK);

        Font buttonFont = Font.font("Helvetica", FontWeight.BOLD, 27);
        TableView<Game> table = new TableView<>();

        TableColumn<Game, String> gameName = new TableColumn<>("Room Name");
        TableColumn<Game, String> description = new TableColumn<>("Description");
        TableColumn<Game, String> user = new TableColumn<>("User");

        gameName.setPrefWidth(200);
        description.setPrefWidth(300);
        user.setPrefWidth(200);

        gameName.setResizable(false);
        description.setResizable(false);
        user.setResizable(false);

        gameName.setCellValueFactory(
                new PropertyValueFactory<Game,String>("gameName")
        );

        description.setCellValueFactory(
                new PropertyValueFactory<Game,String>("description")
        );

        user.setCellValueFactory(
                new PropertyValueFactory<Game,String>("user")
        );

        table.setRowFactory(tv -> {
            TableRow<Game> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Game game = row.getItem();
                    System.out.println("Double click on: " + game.getGameName());
                    player.sendPacket("1001:" + game.getGameName() + ":" + game.getUser());
                }
            });
            return row ;
        });

        table.getColumns().addAll(gameName, description, user);


        ObservableList<Game> items = FXCollections.observableList(Pong.games);
        table.setItems(items);
        table.setPrefWidth(700);
        table.setPrefHeight(400);
        table.setTranslateY(200);
        table.setTranslateX(50);

        // new Image(url)
        Image image = new Image("background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        Pong.mainServices.scheduleAtFixedRate(player::update, 10L, 30L, TimeUnit.MILLISECONDS);

        root.setBackground(background);
        root.getChildren().add(text);
        root.getChildren().add(table);
        stage.close();
        stage.show();
    }

    public void update() {

    }

}
