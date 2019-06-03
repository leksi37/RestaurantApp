package server.networking.ServerSocketHandlers;

import JDBC.MenuItemsReader;
import JDBC.OrderReader;
import JDBC.PasswordReader;
import basicClasses.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.scene.control.PasswordField;
import server.model.ServerModel;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ChefServerSocketHandler implements ServerSocketHandler, Runnable{
    private ServerModel model;
    private Socket socket;

    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    private String connectionId;
//    private PasswordReader passwordReader;

    public ChefServerSocketHandler(ServerModel model, Socket socket){
//        passwordReader = PasswordReader.getInstance();
        this.model=model;
        try{
            inFromClient=new ObjectInputStream(socket.getInputStream());
            outToClient= new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        }
        model.addListener("AddedOrder", this::addOrder);
        model.addListener("AddedToOrder", this::addToOrder);
        model.addListener("ChangedState", this::changedState);
        model.addListener("orderRemoved", this::orderRemoved);
        model.addListener("chefPasswordCheck", this::passwordCheck);
        model.addListener("partialForWaiterSent", this::partialForWaiterSent);
    }

    private void partialForWaiterSent(PropertyChangeEvent propertyChangeEvent) {
        try{
            outToClient.writeObject(new Request(RequestType.SEND_PARTIAL, (Order)propertyChangeEvent.getNewValue()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void passwordCheck(PropertyChangeEvent propertyChangeEvent) {
        RequestType r;
        if((boolean)propertyChangeEvent.getNewValue())
            r = RequestType.CHEF_APPROVED;
        else r = RequestType.CHEF_DISAPPROVED;
        try{
            outToClient.writeObject(new Request(r, null));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void orderRemoved(PropertyChangeEvent propertyChangeEvent) {
        try{
            outToClient.writeObject(new Request(RequestType.ORDER_FINISHED, (String)propertyChangeEvent.getNewValue()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changedState(PropertyChangeEvent propertyChangeEvent) {
        try{
            Order o = new Order((Order)propertyChangeEvent.getNewValue());
            outToClient.writeObject(new Request(RequestType.ITEM_STATE_CHANGED, o));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToOrder(PropertyChangeEvent propertyChangeEvent) {
        try{
            Order o = new Order((Order)propertyChangeEvent.getNewValue());
            outToClient.writeObject(new Request(RequestType.ADDED_TO_ORDER, o));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConnectionId(String id)
    {
        connectionId = id;
    }

    private void addOrder(PropertyChangeEvent propertyChangeEvent) {
        try{
            outToClient.writeObject(new Request(RequestType.NEW_ORDER, (Order)propertyChangeEvent.getNewValue()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while(true){
            try{
                Request r = (Request) inFromClient.readObject();
                switch (r.getType()){
                    case CHEF_PASSWORD_CHECK: {
                        model.chefCheckPassword(new Passwords("chef", (String)r.getObj()));
                        break;
                    }
                    case FETCH_ORDERS : {
                        outToClient.writeObject(new Request(RequestType.FETCH_ORDERS, model.getOrders()));
                        break;
                    }
                    case ITEM_STATE_CHANGED: {
                        Order o = new Order((Order) r.getObj());
                        model.itemStateChanged(o);
                        break;
                    }
                    case SEND_PARTIAL: {
                        model.sendPartial((Order) r.getObj());
                        break;
                    }
                    case ORDER_FINISHED: {
                        model.orderFinished((String) r.getObj());
                        break;
                    }
                    case CHEF_REQUESTS_WAITER:{
                        model.chefRequestsWaiter();
                    }
                }
            } catch (ClassNotFoundException e) {

            } catch (IOException e)
            {
            }
        }

    }

    @Override
    public String getId() {
        return connectionId;
    }
}
