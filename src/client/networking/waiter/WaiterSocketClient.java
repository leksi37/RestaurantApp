package client.networking.waiter;

import basicClasses.Notification;
import client.model.waiter.WaiterModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class WaiterSocketClient implements WaiterClient{
    private WaiterModel waiterModel;
    private Socket socket;
    private WaiterClientSocketHandler waiterClientSocketHandler;

    public WaiterSocketClient(WaiterModel waiterModel, Socket socket) {
        this.waiterModel=waiterModel;
        try{
            this.socket=socket;
            System.out.println("Client connected");
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
    public void gotNotification(Notification notification) {
        waiterModel.notificationReceived(notification);
    }

    @Override
    public void gotPresenceRequest(Notification notification) {
        waiterModel.presenceRequested(notification);
    }
}
