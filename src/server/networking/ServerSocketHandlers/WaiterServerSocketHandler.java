package server.networking.ServerSocketHandlers;

import JDBC.PasswordReader;
import basicClasses.Passwords;
import basicClasses.Request;
import basicClasses.RequestType;
import server.model.ServerModel;

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
        model.addListener("waiterPasswordCheck", this::passwordCheck);
        model.addListener("chefRequestsWaiter", this::chefRequestsWaiter);
        model.addListener("partialForWaiter", this::partialToDeliver);
        model.addListener("customerRequest", this::customerRequest);
        model.addListener("Receipt request", this::receiptRequest);
        model.addListener("orderClosed", this::orderClosed);
    }

    private void orderClosed(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(new Request(RequestType.ORDER_CLOSED, propertyChangeEvent.getNewValue()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void receiptRequest(PropertyChangeEvent changeEvent) {
        try {
            outToClient.writeObject(new Request(RequestType.RECEIPT, changeEvent.getNewValue()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void customerRequest(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(new Request(RequestType.CUSTOMER_REQUESTS_WAITER, propertyChangeEvent.getNewValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chefRequestsWaiter(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(new Request(RequestType.CHEF_REQUESTS_WAITER, propertyChangeEvent.getNewValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void partialToDeliver(PropertyChangeEvent propertyChangeEvent) {
        try {
            outToClient.writeObject(new Request(RequestType.PARTIAL_FOR_WAITER, propertyChangeEvent.getNewValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void passwordCheck(PropertyChangeEvent propertyChangeEvent) {
        RequestType r;
        if((boolean)propertyChangeEvent.getNewValue())
            r = RequestType.WAITER_APPROVED;
        else r = RequestType.WAITER_DISAPPROVED;
        try{
            outToClient.writeObject(new Request(r, null));
        }catch (IOException e) {
            e.printStackTrace();
        }
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
                switch (r.getType()){
                    case WAITER_PASSWORD_CHECK: {
                        model.waiterCheckPassword(new Passwords("waiter", (String)r.getObj()));
                        break;
                    }
                    case WAITER_CLOSE_ORDER:{
                        model.closeOrder((String)r.getObj());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {

            }
        }
    }

    @Override
    public String getId() {
        return connectionId;
    }
}


