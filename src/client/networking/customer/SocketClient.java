package client.networking.customer;

import BasicClasses.MenuItem;
import BasicClasses.Order;
import client.model.customer.CustomerModel;
import client.networking.customer.Client;
import client.networking.customer.ClientSocketHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SocketClient implements Client {

    private CustomerModel model;
    private ClientSocketHandler clientSocketHandler;
    private Socket socket;

    public SocketClient(CustomerModel model){
        this.model=model;
        try{
            socket=new Socket("localHost", 2910);
            clientSocketHandler= new ClientSocketHandler(this,
                    new ObjectOutputStream(socket.getOutputStream()),
                    new ObjectInputStream(socket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t= new Thread(clientSocketHandler);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void addOrderToServer(Order order) {
        clientSocketHandler.addOrderToServer(order);
    }

    @Override
    public void requestMenuCategory(String type) {
        clientSocketHandler.requestCategory(type);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> mi) {
        model.gotMenuItems(mi);
    }

    @Override
    public void getTableId() {
        clientSocketHandler.getTableId();
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
