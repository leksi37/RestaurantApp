package client.networking.chef;

import basicClasses.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ChefClientSocketHandler implements Runnable {
    private ChefClient chefClient;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public ChefClientSocketHandler(ChefClient chefClient, ObjectOutputStream outputStream, ObjectInputStream inputStream){
        this.chefClient = chefClient;
        this.inFromServer=inputStream;
        this.outToServer=outputStream;
    }

    @Override
    public void run() {
        while(true){
            try{
                Request r = (Request) inFromServer.readObject();
                switch (r.getType())
                {
                    case NEW_ORDER:
                    {
                        chefClient.gotOrder((Order) r.getObj());
                        break;
                    }
                    case CHEF_APPROVED:{
                        chefClient.passwordApproved();
                        break;
                    }
                    case CHEF_DISAPPROVED:{
                        chefClient.passwordDisapproved();
                        break;
                    }
                    case FETCH_ORDERS:{
                        chefClient.gotOrders((ArrayList<Order>) r.getObj());
                        break;
                    }
                    case ADDED_TO_ORDER:{
                        Order o = new Order((Order)r.getObj());
                        chefClient.addedToOrder((Order) r.getObj());
                        break;
                    }
                    case ITEM_STATE_CHANGED:{
                        Order o = new Order((Order)r.getObj());
                        chefClient.orderChanged((Order) r.getObj());
                        break;
                    }
                    case ORDER_FINISHED:{
                        chefClient.removeOrder((String)r.getObj());
                        break;
                    }
                    case SEND_PARTIAL:{
                        chefClient.partialSent((Order)r.getObj());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendNotificationToWaiter(String notification){
        try{
            outToServer.writeObject(new Request(RequestType.SEND_NOTIFICATION, notification));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkPassword(String value) {
        try {
            outToServer.writeObject(new Request(RequestType.CHEF_PASSWORD_CHECK, value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchOrders() {
        try {
            outToServer.writeObject(new Request(RequestType.FETCH_ORDERS, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stateChanged(Order order) {
        try {
            outToServer.writeObject(new Request(RequestType.ITEM_STATE_CHANGED, order));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPartial(Order order) {
        try {
            outToServer.writeObject(new Request(RequestType.SEND_PARTIAL, new Order(order)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void orderFinished(String tableId) {
        try {
            outToServer.writeObject(new Request(RequestType.ORDER_FINISHED, tableId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestWaiter() {
        try {
            outToServer.writeObject(new Request(RequestType.CHEF_REQUESTS_WAITER, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
