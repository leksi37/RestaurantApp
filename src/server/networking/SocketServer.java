package server.networking;

import basicClasses.ClientType;
import server.model.ServerModel;
import server.networking.ServerSocketHandlers.ChefServerSocketHandler;
import server.networking.ServerSocketHandlers.CustomerServerSocketHandler;
import server.networking.ServerSocketHandlers.WaiterServerSocketHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerModel model;
    private ObjectInputStream inFromClient;

    public SocketServer(ServerModel model){this.model=model; }

    public void start() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(2910);
            System.out.println("Server started...");
            while (true) {

                Socket socket = welcomeSocket.accept();
                inFromClient = new ObjectInputStream(socket.getInputStream());
                ClientType typeOfClient = null;
                try {
                    typeOfClient = (ClientType) inFromClient.readObject();
                    connectClient(typeOfClient, socket);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException ex) {
        }

    }

    public void connectClient(ClientType type, Socket socket){
        if (type.equals(ClientType.CUSTOMER_CLIENT)) {
            CustomerServerSocketHandler serverSocketHandler = new CustomerServerSocketHandler(model, socket);
            Thread thread = new Thread(serverSocketHandler);
            System.out.println("Customer client connected");
            thread.start();
        }
        else if(type.equals(ClientType.WAITER_CLIENT)){
            WaiterServerSocketHandler serverSocketHandler= new WaiterServerSocketHandler(model, socket);
            Thread thread=new Thread(serverSocketHandler);
            System.out.println("waiter client connected");
            thread.start();
        }
        else if(type == ClientType.CHEF_CLIENT){
            ChefServerSocketHandler serverSocketHandler= new ChefServerSocketHandler(model, socket);
            Thread thread = new Thread(serverSocketHandler);
            System.out.println("chef client connected");
            thread.start();
        }
        else {
        }
    }
}
