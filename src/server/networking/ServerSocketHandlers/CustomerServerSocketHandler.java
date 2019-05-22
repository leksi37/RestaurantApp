package server.networking.ServerSocketHandlers;

import JDBC.OrderReader;
import basicClasses.*;
import JDBC.MenuItemsReader;
import server.model.ServerModel;
import server.networking.ServerSocketHandler;

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
        private MenuItemsReader reader;
        private OrderReader orderReader;

        public CustomerServerSocketHandler(ServerModel model, Socket socket){
            reader = MenuItemsReader.getInstance();
            orderReader = OrderReader.getInstance();
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
                outToClient.writeObject(propertyChangeEvent.getNewValue());
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(true){
                try{
                        Request r = (Request) inFromClient.readObject();
                        System.out.println("REQUEST: "+ r.getType().toString());

                    if(r.getType() == RequestType.GET_MENU_ITEMS){
                        ArrayList<MenuItem> menuItems = reader.getCategory(type.valueOf((String)r.getObj()));
                        try {
                            System.out.println("got here ");
                            outToClient.writeObject(new Request(RequestType.GET_MENU_ITEMS, menuItems));

                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(r.getType() == RequestType.GET_TABLE_ID)
                    {
                        System.out.println(" requesting table id");
                        String s = model.newId(this);
                        System.out.println("table id: "+s);
                        setConnectionId(s);
                        try {
                            outToClient.writeObject(new Request(RequestType.GET_TABLE_ID, s));
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(r.getType() == RequestType.ADD_ORDER)
                    {
                        orderReader.addOrder((Order) r.getObj());

                        try {
                            outToClient.writeObject(new Request(RequestType.ADD_ORDER, null));
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

