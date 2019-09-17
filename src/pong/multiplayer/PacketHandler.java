package pong.multiplayer;

import pong.game.Pong;
import pong.handlers.StringHandler;

public class PacketHandler {

    public void handlePacket(String packet) {
        int packetId = Integer.valueOf(packet.split(":")[0]);
        switch (packetId) {
            case 1:
                return;
            case 2:
                Pong.games = StringHandler.decodeGames(packet);
                Pong.games.forEach(System.out::println);
                return;
            case 3:
                return;
            case 4:
                return;
        }
    }

}
