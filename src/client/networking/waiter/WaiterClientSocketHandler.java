package client.networking.waiter;

import basicClasses.Notification;
import basicClasses.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WaiterClientSocketHandler implements Runnable {
    private WaiterClient waiterClient;
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;

    public WaiterClientSocketHandler(WaiterClient waiterClient, ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.waiterClient = waiterClient;
        inFromServer = inputStream;
        outToServer = outputStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request r = (Request) inFromServer.readObject();
                switch (r.getType()) {
                    case SEND_NOTIFICATION: {
                        waiterClient.gotNotification((Notification) r.getObj());
                        break;
                    }
                    case PRESENCE_REQUIRED: {
                        waiterClient.gotNotification((Notification) r.getObj());
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


}
