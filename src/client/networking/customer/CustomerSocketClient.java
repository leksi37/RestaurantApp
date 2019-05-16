package client.networking.customer;

import BasicClasses.MenuItem;
import BasicClasses.Order;
import client.model.customer.CustomerModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class CustomerSocketClient implements CustomerClient {

    private CustomerModel model;
    private CustomerClientSocketHandler customerClientSocketHandler;
    private Socket socket;

    public CustomerSocketClient(CustomerModel model){
        this.model=model;
        try{
            socket=new Socket("localHost", 2910);
            System.out.println("Client con");
            customerClientSocketHandler = new CustomerClientSocketHandler(this,
                    new ObjectOutputStream(socket.getOutputStream()),
                    new ObjectInputStream(socket.getInputStream()));
            System.out.println("not making it");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t= new Thread(customerClientSocketHandler);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void addOrderToServer(Order order) {
        customerClientSocketHandler.addOrderToServer(order);
    }

    @Override
    public void requestMenuCategory(String type) {
        customerClientSocketHandler.requestCategory(type);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> mi) {
        model.gotMenuItems(mi);
    }

    @Override
    public void getTableId() {
        customerClientSocketHandler.getTableId();
    }

    @Override
    public void returnTableId(String str) {
        model.gotTableId(str);
    }

    @Override
    public void orderAdded() {
        model.orderAdded();
    }

}
