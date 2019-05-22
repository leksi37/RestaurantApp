package server.networking.ServerSocketHandlers;

import basicClasses.Request;
import basicClasses.RequestType;
import server.model.ServerModel;
import server.networking.ServerSocketHandler;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WaiterServerSocketHandler implements ServerSocketHandler, Runnable {
    private ServerModel model;
    private Socket socket;

    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    private String connectionId;

    public WaiterServerSocketHandler(ServerModel model, Socket socket) {
        this.model = model;
        try {
            inFromClient = new ObjectInputStream(socket.getInputStream());
            outToClient = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addListener("Notification added", this::sendNotification);
    }

    private void sendNotification(PropertyChangeEvent changeEvent) {
        try {
            outToClient.writeObject(new Request(RequestType.SEND_NOTIFICATION, changeEvent.getNewValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConnectionId(String id)
    {
        connectionId = id;
    }

    @Override
    public void run() {

        // for now we are never sending anything to the server from waiter

        while (true) {
//            try{
//                Request r = (Request) inFromClient.readObject();
//
//                if(r.getType() == RequestType.SEND_NOTIFICATION{
//                }
//            } catch (ClassNotFoundException | IOException e) {

            // model.removeConnection(connectionId);

            try {
                Request r = (Request) inFromClient.readObject();

            } catch (IOException | ClassNotFoundException e) {
                model.removeConnection(connectionId);

            }

        }
    }

    @Override
    public String getId() {
        return connectionId;
    }
}


