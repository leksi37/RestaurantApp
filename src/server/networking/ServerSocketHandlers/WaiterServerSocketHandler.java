package server.networking.ServerSocketHandlers;

import JDBC.PasswordReader;
import basicClasses.Passwords;
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
    private PasswordReader passwordReader;

    public WaiterServerSocketHandler(ServerModel model, Socket socket) {
        this.model = model;
        passwordReader = PasswordReader.getInstance();
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
            try {
                Request r = (Request) inFromClient.readObject();
                System.out.println(r.getType());
            switch (r.getType()){
                case WAITER_PASSWORD_CHECK: {
                    System.out.println("entered if statement");
                    RequestType t;
                    Passwords password = passwordReader.getPassword("waiter");
                    System.out.println("waiter ssh, in db: " + password.getPassword() + "you typed: " + (String)r.getObj());
                    if(password.getPassword().equals(r.getObj()))
                        t = RequestType.WAITER_APPROVED;
                    else
                        t = RequestType.WAITER_DISAPPROVED;

                    outToClient.writeObject(new Request(t, null));
                    break;
                }
            }
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


