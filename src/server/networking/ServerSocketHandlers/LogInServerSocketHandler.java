package server.networking.ServerSocketHandlers;

import basicClasses.ClientType;
import basicClasses.Passwords;
import basicClasses.Request;
import basicClasses.RequestType;
import server.model.ServerModel;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LogInServerSocketHandler implements Runnable{

    private ServerModel model;
    private Socket socket;
    private ClientType type;

    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    public LogInServerSocketHandler(ServerModel model, Socket socket){
        this.model=model;
        this.socket=socket;
        try{
            inFromClient=new ObjectInputStream(socket.getInputStream());
            outToClient= new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        }
        model.addListener("passwordCheck", this::passwordCheck);
    }

    @Override
    public void run() {
        try{
            Object o = inFromClient.readObject();
            if(o instanceof ClientType) {
                ClientType t= (ClientType)o;
                connectClient(t);
            }
            else if(o instanceof Request) {
                Request r = (Request) o;
                System.out.println("REQUEST: " + r.getType().toString());

                switch (r.getType()) {

                    case CHEF_PASSWORD_CHECK: {
                        System.out.println("asking server model for passsword");
                        model.checkPassword(new Passwords("chef", (String) r.getObj()));
                        break;
                    }
                    case WAITER_PASSWORD_CHECK: {
                        model.checkPassword(new Passwords("waiter", (String)r.getObj()));
                        break;
                    }
                    case CONNECT_CLIENT:{
                        passwordApproved();
                    }
                }
            }
        } catch (ClassNotFoundException e) {

        } catch (IOException e)
        {
            model.removeConnection();
        }
    }

    private void passwordCheck(PropertyChangeEvent propertyChangeEvent) {
        RequestType r=null;
        if(propertyChangeEvent.getOldValue().equals("chef"))
        {
            System.out.println("got answer from server model about password");
            if((boolean)propertyChangeEvent.getNewValue()){
                r = RequestType.CHEF_APPROVED;
            }
            else r = RequestType.CHEF_DISAPPROVED;
        }
        else if(propertyChangeEvent.getOldValue().equals("waiter")) {
            if ((boolean) propertyChangeEvent.getNewValue()){
                r = RequestType.WAITER_APPROVED;
            }
            else r = RequestType.WAITER_DISAPPROVED;
        }

        try{
            outToClient.writeObject(new Request(r, null));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectClient(ClientType type){
        if (type.equals(ClientType.CUSTOMER_CLIENT)) {
            CustomerServerSocketHandler serverSocketHandler = new CustomerServerSocketHandler(model, socket);
            Thread thread = new Thread(serverSocketHandler);
            System.out.println("Customer client connected");
            thread.start();
        }
        else if(type.equals(ClientType.WAITER_CLIENT)){
            this.type=type;
        }
        else if(type == ClientType.CHEF_CLIENT){
           this.type=type;
        }
        else {
            System.out.println("None of the above client was connected! !ERROR!");
        }
    }

    private void passwordApproved(){
        if(type.equals(ClientType.WAITER_CLIENT)){
            WaiterServerSocketHandler serverSocketHandler= new WaiterServerSocketHandler(model, socket);
            Thread thread=new Thread(serverSocketHandler);
            System.out.println("waiter client connected");
            thread.start();
        }
        else if(type == ClientType.CHEF_CLIENT){
           ChefServerSocketHandler serverSocketHandler= new ChefServerSocketHandler(model, socket);
            Thread thread = new Thread(serverSocketHandler);
            System.out.println("chef client connected");
            thread.start();
        }
    }

}
