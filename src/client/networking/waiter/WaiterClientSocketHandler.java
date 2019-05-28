package client.networking.waiter;

import basicClasses.Notification;
import basicClasses.Request;
import basicClasses.RequestType;

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
                        waiterClient.gotPresenceRequest((Notification) r.getObj());
                        break;
                    }
                    case WAITER_APPROVED: {
                        waiterClient.passwordApproved();
                        break;
                    }
                    case WAITER_DISAPPROVED: {
                        waiterClient.passwordDisapproved();
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void checkPassword(String value) {
        try {
            System.out.println("wsh: " + value);
            outToServer.writeObject(new Request(RequestType.WAITER_PASSWORD_CHECK, value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
