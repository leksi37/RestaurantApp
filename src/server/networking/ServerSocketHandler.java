//package server.networking;
//
//import BasicClasses.*;
//import JDBC.MenuItemsReader;
//
//import JDBC.OrderReader;
//import com.sun.org.apache.xpath.internal.operations.Or;
//import org.postgresql.util.PSQLException;
//import server.model.ServerModel;
//
//import java.beans.PropertyChangeEvent;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class ServerSocketHandler implements Runnable {
//
//    private ServerModel model;
//    private Socket socket;
//
//    private ObjectInputStream inFromClient;
//    private ObjectOutputStream outToClient;
//
//    private String connectionId;
//    private MenuItemsReader reader;
//    private OrderReader orderReader;
//
//    public ServerSocketHandler(ServerModel model, Socket socket){
//        reader = MenuItemsReader.getInstance();
//        orderReader = OrderReader.getInstance();
//        this.model=model;
//        try{
//            inFromClient=new ObjectInputStream(socket.getInputStream());
//            outToClient= new ObjectOutputStream(socket.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        model.addListener("AddedOrder", this::addOrder);
//    }
//
//    private void setConnectionId(String id)
//    {
//        connectionId = id;
//    }
//
//    private void addOrder(PropertyChangeEvent propertyChangeEvent) {
//        try{
//            outToClient.writeObject((Order)propertyChangeEvent.getNewValue());
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//
//        while(true){
//            try{
//                Request r = (Request) inFromClient.readObject();
//
//                if(r.getType() == RequestType.GET_MENU_ITEMS){
//                    ArrayList<MenuItem> menuItems = reader.getCategory(type.valueOf((String)r.getObj()));
//                    try {
//                        outToClient.writeObject(new Request(RequestType.GET_MENU_ITEMS, menuItems));
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//                else if(r.getType() == RequestType.GET_TABLE_ID)
//                {
//                    String s = model.newId(this);
//                    setConnectionId(s);
//                    try {
//                        outToClient.writeObject(new Request(RequestType.GET_TABLE_ID, s));
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//                else if(r.getType() == RequestType.ADD_ORDER)
//                {
//                        orderReader.addOrder((Order) r.getObj());
//
//                    try {
//                        outToClient.writeObject(new Request(RequestType.ADD_ORDER, null));
//                      ////////  outToClient.writeObject(new Request(RequestType.GET_ORDER, r.getType()));
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            } catch (ClassNotFoundException e) {
//
//            } catch (IOException e)
//            {
//                model.removeConnection(connectionId);
//            }
//        }
//
//    }
//}
