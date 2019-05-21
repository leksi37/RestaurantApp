package server.networking.ServerSocketHandlers;

import JDBC.MenuItemsReader;
import JDBC.OrderReader;
import JDBC.PasswordReader;
import basicClasses.*;
import javafx.scene.control.PasswordField;
import server.model.ServerModel;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ChefServerSocketHandler implements ServerSocketHandler, Runnable{
    private ServerModel model;
    private Socket socket;

    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    private String connectionId;
    private MenuItemsReader reader;
    private OrderReader orderReader;
    private PasswordReader passwordReader;

    public ChefServerSocketHandler(ServerModel model, Socket socket){
        reader = MenuItemsReader.getInstance();
        orderReader = OrderReader.getInstance();
        passwordReader = PasswordReader.getInstance();
        this.model=model;
        try{
            inFromClient=new ObjectInputStream(socket.getInputStream());
            outToClient= new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addListener("AddedOrder", this::addOrder);
    }

    private void setConnectionId(String id)
    {
        connectionId = id;
    }

    private void addOrder(PropertyChangeEvent propertyChangeEvent) {
        try{
            outToClient.writeObject((Order)propertyChangeEvent.getNewValue());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while(true){
            try{
                Request r = (Request) inFromClient.readObject();

                if(r.getType() == RequestType.CHEF_PASSWORD_CHECK){
                    RequestType t;
                    Passwords password = passwordReader.getPassword("chef");
                    if(password.equals(r.getObj()))
                        t = RequestType.CHEF_APPROVED;
                    else
                        t = RequestType.CHEF_DISAPPROVED;
                    try {
                        outToClient.writeObject(new Request(t, null));
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(r.getType() == RequestType.GET_CONNECTION_ID)
                {
                    String s = model.newId(this);
                    setConnectionId(s);
                    try {
                        outToClient.writeObject(new Request(RequestType.GET_CONNECTION_ID, s));
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(r.getType() == RequestType.ADD_ORDER)
                {
                    orderReader.addOrder((Order) r.getObj());

                    try {
                        outToClient.writeObject(new Request(RequestType.ADD_ORDER, null));
                        ////////  outToClient.writeObject(new Request(RequestType.GET_ORDER, r.getType()));
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            } catch (ClassNotFoundException e) {

            } catch (IOException e)
            {
                model.removeConnection(connectionId);
            }
        }

    }

    @Override
    public String getId() {
        return connectionId;
    }
}
