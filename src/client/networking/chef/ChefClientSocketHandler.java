package client.networking.chef;

import basicClasses.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChefClientSocketHandler implements Runnable {
    private ChefClient chefClient;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public ChefClientSocketHandler(ChefClient chefClient, ObjectOutputStream outputStream, ObjectInputStream inputStream){
        this.chefClient = chefClient;
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
                        chefClient.gotOrder((Order) r.getObj());
                        break;
                    }
                    case CHEF_APPROVED:{
                        chefClient.passwordApproved();
                    }
                    case CHEF_DISAPPROVED:{
                        chefClient.passwordDisapproved();
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

    public void checkPassword(String value) {
        try {
            System.out.println("csh" + value);
            outToServer.writeObject(new Request(RequestType.CHEF_PASSWORD_CHECK, value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
