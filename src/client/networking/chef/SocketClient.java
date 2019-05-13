package client.networking.chef;

import BasicClasses.Order;
import client.model.chef.ChefModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient implements Client {
    private ChefModel model;
    private ClientSocketHandler clientSocketHandler;
    private Socket socket;

    public SocketClient(ChefModel model){
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
    public void sendNotification(String notification) {
        clientSocketHandler.sendNotificationToWaiter(notification);
    }

    @Override
    public void gotOrder(Order order) {
        model.orderAdded();
    }
}
