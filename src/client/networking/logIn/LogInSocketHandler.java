package client.networking.logIn;

import basicClasses.ClientType;
import basicClasses.Request;
import basicClasses.RequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LogInSocketHandler implements Runnable {

    private LogIn client;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public LogInSocketHandler(LogIn client, ObjectOutputStream outToServer, ObjectInputStream inFromServer) {
        this.client = client;
        this.outToServer = outToServer;
        this.inFromServer = inFromServer;
    }

    @Override
    public void run() {
        while(true){
            try{
                Request r = (Request) inFromServer.readObject();
                switch (r.getType())
                {
                    case WAITER_APPROVED:{
                        client.passwordCheckResult(r.getType());
                        break;
                    }
                    case WAITER_DISAPPROVED:{
                        client.passwordCheckResult(r.getType());
                        break;
                    }
                    case CHEF_APPROVED:{
                        client.passwordCheckResult(r.getType());
                        break;
                    }
                    case CHEF_DISAPPROVED:{
                        client.passwordCheckResult(r.getType());
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void connectClient(ClientType type){
        try {
            outToServer.writeObject(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkPassword(String value){
        if(client.getClientType().equals(ClientType.WAITER_CLIENT)) {
            try {
                outToServer.writeObject(new Request(RequestType.WAITER_PASSWORD_CHECK, value));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(client.getClientType().equals(ClientType.CHEF_CLIENT)) {
            try {
                outToServer.writeObject(new Request(RequestType.CHEF_PASSWORD_CHECK, value));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //used to create server socket handlers after password aproved
    public void connectApprovedClient(ClientType type){
        try {
            outToServer.writeObject(new Request(RequestType.CONNECT_CLIENT, type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
