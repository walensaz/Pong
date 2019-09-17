package server;

import server.handlers.ConnectionHandler;
import server.objects.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Launch {

    public static ArrayList<Game> availableGames = new ArrayList<>();
    public static ServerSocket serverSocket;
    public static ScheduledExecutorService mainServices;

    private static ConnectionHandler handler;


    public static void main(String[] args) {
        mainServices = Executors.newScheduledThreadPool(10);
        availableGames.add(new Game("1stgame", "a new game!", "yeeet"));
        initialize();
        try {
            serverSocket = new ServerSocket(5555);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    private static void initialize() {
        handler = new ConnectionHandler();
        mainServices.scheduleAtFixedRate(handler::run, 10L, 10L, TimeUnit.MILLISECONDS);
    }


}
