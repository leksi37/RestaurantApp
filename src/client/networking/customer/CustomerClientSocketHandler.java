package client.networking.customer;


import basicClasses.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CustomerClientSocketHandler implements Runnable {

    private CustomerClient customerClient;

    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public CustomerClientSocketHandler(CustomerClient customerClient, ObjectOutputStream outputStream, ObjectInputStream inputStream){
        this.customerClient = customerClient;
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
                        case GET_MENU_ITEMS:
                        {
                            ArrayList<MenuItem> mi = (ArrayList<MenuItem>)r.getObj();
                            customerClient.gotMenuItems(mi);
                            break;
                        }
                        case GET_TABLE_ID:
                        {
                            customerClient.returnTableId((String)r.getObj());
                            break;
                        }
                        case ADD_ORDER:
                        {
                            customerClient.orderAdded();
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

    }

    public void addOrderToServer(Order order){
        try{
            outToServer.writeObject(new Request(RequestType.ADD_ORDER, order));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestCategory(String type) {
        try {
            outToServer.writeObject(new Request(RequestType.GET_MENU_ITEMS, type));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTableId() {
        try {
            outToServer.writeObject(new Request(RequestType.GET_TABLE_ID, null));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
