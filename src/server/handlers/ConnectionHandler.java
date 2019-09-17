package server.handlers;

import server.Launch;
import server.objects.Connection;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ConnectionHandler {

    private ScheduledExecutorService playerService;
    private ArrayList<Connection> connections;

    public ConnectionHandler() {
        playerService = Executors.newScheduledThreadPool(400);
        this.connections = new ArrayList<>();
    }

    public void run() {
        Socket newSocket;
        try {
            newSocket = Launch.serverSocket.accept();
            if (newSocket != null) {
                addConnection(newSocket);
                System.out.println("Accepted new connection!");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addConnection(Socket socket) {
        Connection connection = new Connection(socket);
        Future future = playerService.scheduleAtFixedRate(connection::update, 40L, 40L, TimeUnit.MILLISECONDS);
        connection.setFuture(future);
        connections.add(connection);
    }

    public void removeConnection(Connection connection) {
        Connection currentConnection = connections.stream().filter(connection1 -> connection1.equals(connection)).collect(Collectors.toList()).get(0);
        currentConnection.cancel();
        connections.remove(connection);
    }

    public void checkConnections() {

    }


}
