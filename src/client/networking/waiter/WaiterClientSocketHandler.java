package client.networking.waiter;

import basicClasses.Notification;
import basicClasses.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WaiterClientSocketHandler implements Runnable {
    private WaiterSocketClient waiterSocketClient;
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;

    public WaiterClientSocketHandler(WaiterSocketClient waiterSocketClient, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.waiterSocketClient = waiterSocketClient;
        inFromServer = objectInputStream;
        outToServer = objectOutputStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request r = (Request) inFromServer.readObject();
                switch (r.getType()) {
                    case SEND_NOTIFICATION: {
                        waiterSocketClient.gotNotification((Notification) r.getObj());
                        break;
                    }
                    case PRESENCE_REQUIRED: {
                        waiterSocketClient.gotPresenceRequest((Notification) r.getObj());
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


}
