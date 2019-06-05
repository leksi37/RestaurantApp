package client.networking.chef;

import basicClasses.Order;
import client.model.chef.ChefModel;
import client.networking.customer.CustomerClientSocketHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ChefSocketClient implements ChefClient {
    private ChefModel model;
    private ChefClientSocketHandler chefClientSocketHandler;
    private Socket socket;

    public ChefSocketClient(ChefModel model, Socket socket){
        this.model=model;
        try{
            this.socket=socket;
            chefClientSocketHandler = new ChefClientSocketHandler(this,
                    new ObjectOutputStream(socket.getOutputStream()),
                    new ObjectInputStream(socket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t= new Thread(chefClientSocketHandler);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void gotOrder(Order order) {
        model.orderAdded(order);
    }

    @Override
    public void checkPassword(String value) {
        chefClientSocketHandler.checkPassword(value);
    }

    @Override
    public void passwordApproved() {
        model.passwordApproved();
    }

    @Override
    public void passwordDisapproved() {
        model.passwordDisapproved();
    }

    @Override
    public void fetchOrders() {
        chefClientSocketHandler.fetchOrders();
    }

    @Override
    public void gotOrders(ArrayList<Order> obj) {
        model.gotOrders(obj);
    }

    @Override
    public void addedToOrder(Order obj) {
        model.addedToOrder(obj);
    }

    @Override
    public void itemStateChanged(Order order) {
        chefClientSocketHandler.stateChanged(order);
    }

    @Override
    public void orderChanged(Order obj) {
        model.addedToOrder(obj);
    }

    @Override
    public void sendPartial(Order order) {
        chefClientSocketHandler.sendPartial(order);
    }

    @Override
    public void orderFinished(String tableId) {
        chefClientSocketHandler.orderFinished(tableId);
    }

    @Override
    public void removeOrder(String obj) {
        model.removeOrder(obj);
    }

    @Override
    public void requestWaiter() {
        chefClientSocketHandler.requestWaiter();
    }

    @Override
    public void partialSent(Order obj) {
        model.partialSent(obj);
    }
}
