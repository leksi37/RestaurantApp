package server.networking.ServerSocketHandlers;

import JDBC.OrderReader;
import basicClasses.*;
import JDBC.MenuItemsReader;
import server.model.ServerModel;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


    public class CustomerServerSocketHandler implements ServerSocketHandler, Runnable {

        private ServerModel model;

        private ObjectInputStream inFromClient;
        private ObjectOutputStream outToClient;

        private String connectionId;
//        private MenuItemsReader reader;

        public CustomerServerSocketHandler(ServerModel model, Socket socket){
//            reader = MenuItemsReader.getInstance();
            this.model=model;
            try{
                inFromClient=new ObjectInputStream(socket.getInputStream());
                outToClient= new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            model.addListener("AddedOrder", this::addOrder);
            model.addListener("AddedToOrder", this::addOrder);
        }

        private void addOrder(PropertyChangeEvent propertyChangeEvent) {
            try{
                outToClient.writeObject(new Request(RequestType.ADD_ORDER, null));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(true){
                try{
                        Request r = (Request) inFromClient.readObject();

                    if(r.getType() == RequestType.GET_MENU_ITEMS){
                        try {
                            outToClient.writeObject(new Request(RequestType.GET_MENU_ITEMS, model.getMenuItems(CategoryType.valueOf((String)r.getObj()))));

                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(r.getType() == RequestType.GET_TABLE_ID)
                    {
                        connectionId = model.newId();
                        try {
                            outToClient.writeObject(new Request(RequestType.GET_TABLE_ID, connectionId));
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(r.getType() == RequestType.RECEIPT)
                    {
                        String id = (String) r.getObj();
                        model.requestReceipt(id);
                    }
                    else if(r.getType() == RequestType.ADD_ORDER)
                    {
                        model.addOrder((Order) r.getObj());
                    }
                    else if(r.getType() == RequestType.CUSTOMER_REQUESTS_WAITER)
                    {
                        model.customerRequest((String) r.getObj());
                    }

                } catch (ClassNotFoundException e) {

                } catch (IOException e)
                {
                    model.removeConnection();
                }
            }

        }

        @Override
        public String getId() {
            return connectionId;
        }
    }

