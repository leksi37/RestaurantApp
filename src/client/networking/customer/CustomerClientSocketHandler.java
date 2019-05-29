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
                            String s=(String)r.getObj();
                            customerClient.returnTableId((String)r.getObj());
                            System.out.println("reading table id  "+s);
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
            System.out.println("csh " + order);
            Order o = new Order(order);
            outToServer.writeObject(new Request(RequestType.ADD_ORDER, o));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestCategory(String type) {
        System.out.println(" \n\n requested menu items   "+type);
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
            System.out.println("  requested id ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestWaiter(String tableId) {
        try {
            outToServer.writeObject(new Request(RequestType.CUSTOMER_REQUESTS_WAITER, tableId));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void requestReceipt(String tableId) {
        try {
            outToServer.writeObject(new Request(RequestType.RECEIPT, tableId));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
