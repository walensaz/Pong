package pong.handlers;

import pong.multiplayer.Game;

import java.util.ArrayList;

public class StringHandler {

    public static ArrayList<Game> decodeGames(String content) {
        ArrayList<Game> finalGames = new ArrayList<>();
        String[] games = content.split(":");
        System.out.println("Decoding games: " + content);
        if(games.length > 2) {
            for (int i = 1; i < games.length; i += 3) {
                String name = games[i];
                String desc = games[i + 1];
                String user = games[i + 2];
                finalGames.add(new Game(name, desc, user));
            }
        } else {
            finalGames.add(new Game("", "Not available games", ""));
        }
        return finalGames;
    }

}
