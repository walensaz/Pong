package server.handlers;

import server.Launch;
import server.objects.Game;

public class StringHandler {

    public static String getGames() {
        String games = "";
        for(Game game : Launch.availableGames) {
            games += game.getGameName() + ":" + game.getDescription() + ":" + game.getUser();
        }
        return games;
    }

}
