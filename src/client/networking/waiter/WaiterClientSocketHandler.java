package client.networking.waiter;

import basicClasses.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WaiterClientSocketHandler implements Runnable {
    @Override
    public void run() {

    }
    /*private WaiterClient waiterClient;
    pr+ivate ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public WaiterClientSocketHandler(WaiterClient waiterClient, ObjectOutputStream outputStream, ObjectInputStream inputStream){
        this.waiterClient = waiterClient;
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

                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }*/


}
