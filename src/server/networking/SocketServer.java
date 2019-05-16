package server.networking;

import BasicClasses.clients;
import server.model.ServerModel;
import server.networking.ServerSocketHandlers.CustomerServerSocketHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerModel model;
    private ObjectInputStream inFromClient;
    public SocketServer(ServerModel model){this.model=model;
    }

    public void start() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(2910);
            System.out.println("Server started...");
            while (true) {
                Socket socket = welcomeSocket.accept();
                inFromClient = new ObjectInputStream(socket.getInputStream());
                clients typeOfClient = null;
                try {
                    typeOfClient = (clients) inFromClient.readObject();
                    System.out.println(typeOfClient);
                    inFromClient.close();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                if (typeOfClient.equals(clients.CUSTOMER_CLIENT)) {
                    CustomerServerSocketHandler serverSocketHandler = new CustomerServerSocketHandler(model, socket);
                    Thread thread = new Thread(serverSocketHandler);
                    System.out.println("Customer client connected");
                    thread.start();
                }
//                   else if(typeOfClient.equals(clients.WAITER_CLIENT)){
//                        WaiterServerSocketHandler serverSocketHandler= new WaiterServerSocketHandler(model, socket);
//                        Thread thread=new Thread(serverSocketHandler);
//                        System.out.println("Waiter client connected");
//                        thread.start();
//                   }
//                   else if(typeOfClient.equals(clients.CHEF_CLIENT)){
//                        ChefServerSocketHandler serverSocketHandler= new ChefServerSocketHandler(model, socket);
//                        Thread thread = new Thread(serverSocketHandler);
//                        System.out.println("Chef client connected");
//                        thread.start();
//                    }
//                    else if(typeOfClient.equals(clients.MANAGER_CLIENT)){
//                        ManagerServerSocketHandler serverSocketHandler= new ManagerServerSocketHandler(model, socket);
//                        Thread thread=new Thread(serverSocketHandler);
//                        System.out.println("Manager client connected");
//                        thread.start();
//                    }
                else {
                    System.out.println("None of the above client was connected! !ERROR!");
                }
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
