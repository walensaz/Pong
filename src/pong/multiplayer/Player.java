package pong.multiplayer;

import java.util.concurrent.Future;

public class Player {

    private Connection connection;
    private Future<?> future;

    public Player(int port) {
        connection = new Connection(port);
    }


    public void sendPacket(String info) {
        this.connection.write(info);
    }

    public void setFuture(Future<?> future) {
        this.future = future;
    }

    public void update() {
        this.connection.update();
    }

    public void cancel() {
        future.cancel(true);
    }

}
