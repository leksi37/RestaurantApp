package client.networking.chef;

import basicClasses.Order;
import client.model.chef.ChefModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChefSocketChefClient implements ChefClient {
    private ChefModel model;
    private ChefClientSocketHandler chefClientSocketHandler;
    private Socket socket;

    public ChefSocketChefClient(ChefModel model){
        this.model=model;
        try{
            socket=new Socket("localHost", 2910);
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
    public void sendNotification(String notification) {
        chefClientSocketHandler.sendNotificationToWaiter(notification);
    }

    @Override
    public void gotOrder(Order order) {
        model.orderAdded();
    }
}
