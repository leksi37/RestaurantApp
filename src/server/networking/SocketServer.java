package server.networking;

import server.model.ServerModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerModel model;

    public SocketServer(ServerModel model){this.model=model;}

    public void start(){
        try{
            ServerSocket welcomeSocket=new ServerSocket(2910);
            System.out.println("Server started...");
            while(true){
                Socket socket= welcomeSocket.accept();
                System.out.println("Client connected");
                ServerSocketHandler serverSocketHandler= new ServerSocketHandler(model, socket);
                Thread thread=new Thread(serverSocketHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
