package server;

import server.model.ServerModel;
import server.networking.SocketServer;

public class StartServer {
    public static void main(String[] args) {
        ServerModel server= new ServerModel();
        SocketServer socketServer= new SocketServer(server);
        socketServer.start();
    }
}
