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
            System.out.println("csh" + value);
            outToServer.writeObject(new Request(RequestType.CHEF_PASSWORD_CHECK, value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchOrders() {
        try {
            System.out.println("chef csh");
            outToServer.writeObject(new Request(RequestType.FETCH_ORDERS, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
