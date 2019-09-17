package server.objects;

import server.handlers.PacketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Future;

public class Connection {

    private Future<?> future;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private PacketHandler handler;

    public Connection(Socket socket) {
        handler = new PacketHandler(this);
        try {
            this.socket = socket;
            this.writer = new PrintWriter(socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String info) {
        writer.println(info);
        writer.flush();
    }

    public void update() {
        String temp;
        try {
            if ((temp = reader.readLine()) != null) {
                handler.handlePacket(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.cancel();
        }
    }

    public void cancel() {
        future.cancel(true);
    }

    public void setFuture(Future<?> future) {
        this.future = future;
    }
}
