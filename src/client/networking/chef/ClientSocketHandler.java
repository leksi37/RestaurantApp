package client.networking.chef;

import BasicClasses.Order;
import BasicClasses.Request;
import BasicClasses.RequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientSocketHandler implements Runnable {
    private Client client;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public ClientSocketHandler(Client client, ObjectOutputStream outputStream, ObjectInputStream inputStream){
        this.client = client;
        this.inFromServer=inputStream;
        this.outToServer=outputStream;
    }

    @Override
    public void run() {
        while(true){
            try{
                Request r = (Request) inFromServer.readObject();
                switch (r.getType())
                {
                    case GET_ORDER_CHEF:
                    {
                        client.gotOrder((Order) r.getObj());
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendNotificationToWaiter(String notification){
        try{
            outToServer.writeObject(new Request(RequestType.SEND_NOTIFICATION, notification));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
