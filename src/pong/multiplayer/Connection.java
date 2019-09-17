package pong.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {

    private Socket socket;
    private int port;
    private PrintWriter writer;
    private BufferedReader reader;
    private PacketHandler handler;

    public Connection(int port) {
        this.port = port;
        handler = new PacketHandler();
        try {
            this.socket = new Socket("0.0.0.0", port);
            this.writer = new PrintWriter(socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(IOException e) {
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
        } catch(IOException e) {
            e.printStackTrace();
        }
    }




}
