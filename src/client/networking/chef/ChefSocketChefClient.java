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

public class ChefSocketChefClient implements ChefClient {
    private ChefModel model;
    private ChefClientSocketHandler chefClientSocketHandler;
    private Socket socket;

    public ChefSocketChefClient(ChefModel model, Socket socket){
        this.model=model;
        try{
            this.socket=socket;
            System.out.println("Chef connected");
            chefClientSocketHandler = new ChefClientSocketHandler(this,
                    new ObjectOutputStream(socket.getOutputStream()),
                    new ObjectInputStream(socket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        this.model=model;
//        try{
//            socket=new Socket("localhost", 2910);
//            chefClientSocketHandler = new ChefClientSocketHandler(this,
//                    new ObjectOutputStream(socket.getOutputStream()),
//                    new ObjectInputStream(socket.getInputStream()));
//            System.out.println("Creating the socket handler");
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Thread t= new Thread(chefClientSocketHandler);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void sendNotification(String notification) {
        chefClientSocketHandler.sendNotificationToWaiter(notification);
    }

    @Override
    public void gotOrder(Order order) {
        model.orderAdded(order);
    }

    @Override
    public void checkPassword(String value) {
        System.out.println("client" + value);
        chefClientSocketHandler.checkPassword(value);
    }

    @Override
    public void passwordApproved() {
        model.passwordApproved();
    }

    @Override
    public void passwordDisapproved() {
        System.out.println("password disapproved");
        model.passwordDisapproved();
    }

    @Override
    public void fetchOrders() {
        System.out.println("chef client");
        chefClientSocketHandler.fetchOrders();
    }

    @Override
    public void gotOrders(ArrayList<Order> obj) {
        System.out.println("chef client " + obj.size());
        model.gotOrders(obj);
    }

    @Override
    public void addedToOrder(Order obj) {
        model.addedToOrder(obj);
    }
}
