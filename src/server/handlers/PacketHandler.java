package server.handlers;

import server.Launch;
import server.objects.Connection;

public class PacketHandler {
    private Connection connection;

    public PacketHandler(Connection connection) {
        this.connection = connection;
    }

    public void handlePacket(String packet) {
        int packetId = Integer.valueOf(packet.split(":")[0]);
        String[] content = packet.split(":")[1].split(".");
        switch (packetId) {
            case 100:
                System.out.println(packet);
                String outPacket = "2:" + StringHandler.getGames();
                connection.write(outPacket);
                return;
            case 1000:
                return;
            case 2000:
                return;
            case 3000:
                return;
            case 4000:
                return;


        }
        System.out.println(packet);
    }
}
