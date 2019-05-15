package client.networking.waiter;


import client.model.chef.ChefModel;
import client.model.waiter.WaiterModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class WaiterSocketWaiterClient implements WaiterClient {
    private WaiterModel model;
    private WaiterClientSocketHandler waiterClientSocketHandler;
    private Socket socket;

    public WaiterSocketWaiterClient(WaiterModel model){
        this.model=model;
        try{
            socket=new Socket("localHost", 2910);
            waiterClientSocketHandler = new WaiterClientSocketHandler(this,
                    new ObjectOutputStream(socket.getOutputStream()),
                    new ObjectInputStream(socket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t= new Thread(waiterClientSocketHandler);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void gotNotification(String notification) {
        model.notificationReceived(notification);
    }
}
