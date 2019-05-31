package client.networking.customer;

import basicClasses.*;
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

    public CustomerSocketClient(CustomerModel model, Socket socket){
        this.model=model;
        try{
            this.socket=socket;
            System.out.println("Client connected");
            customerClientSocketHandler = new CustomerClientSocketHandler(this,
                    new ObjectOutputStream(socket.getOutputStream()),
                    new ObjectInputStream(socket.getInputStream()));
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
        System.out.println("customer client " + order);
        customerClientSocketHandler.addOrderToServer(order);
    }

    @Override
    public void requestMenuCategory(String type) {
        System.out.println("type = [" + type + "]");
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
        System.out.println("sent id to model     -> "+str);
    }

    @Override
    public void orderAdded() {
        model.orderAdded();
    }

    @Override
    public void requestWaiter(String tableId) {
        customerClientSocketHandler.requestWaiter(tableId);
    }

    @Override
    public void requestReceipt(String tableId) {
        customerClientSocketHandler.requestReceipt(tableId);
    }

    @Override
    public void orderClosed(String obj) {
        model.orderClosed(obj);
    }


}
