package JDBC;

import basicClasses.ItemQuantity;
import basicClasses.ItemState;
import basicClasses.Order;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderReader {
    private JDBC db;
    private static OrderReader instance;

    private OrderReader() {
        db = JDBC.getInstance();
    }

    public static OrderReader getInstance()
    {
        if(instance == null)
        {
            instance = new OrderReader();
        }
        return instance;
    }

    public void addOrder(Order order)
    {
        try {
            db.insert("Orders", order.dbFormat());
            ArrayList<String> orderItems = order.dbFormatItems();
            for(int i = 0; i < orderItems.size(); ++i)
            {
                db.insert("OrderItem", orderItems.get(i));
            }
        }
        catch (SQLException e)
        {
            addToOrder(order);
        }
    }

    private ItemQuantity toItemQuantity(ResultSet rs)
    {
        ItemQuantity iq = null;
        String id;
        int quantity;
        ItemState state;
        try {
            id = rs.getString("id");
            quantity = rs.getInt("quantity");
            state = ItemState.valueOf(rs.getString("state"));
            iq = new ItemQuantity(id, quantity, state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iq;
    }

    private Order getRow(ResultSet rs)
    {
        String tableId;
        Order o;
        try {
            if(rs.next())
            {
                tableId = rs.getString("tableid");
                o = new Order(tableId);
                o.addItem(toItemQuantity(rs));
                return o;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Order convertToOrder(ResultSet rs)
    {
        Order o;
        try {
            if(rs.next())
            {
                o = new Order(rs.getString("tableId"));
                o.setNote(rs.getString("note"));
                return o;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order readOrder(String tableId)
    {
        ResultSet rs = db.get("Orders", "tableid = '" + tableId + "'");
        Order o = convertToOrder(rs);
        rs = db.get("OrderItem", "tableid = '" + tableId + "'");
        if(o == null)
            return null;
        Order aux = getRow(rs);
        while(aux != null)
        {
            o.addItem(aux.getItemsWithQuantity().get(0));
            aux = getRow(rs);
        }
        return o;
    }

    public ArrayList<Order> readAllOrders()
    {
        ResultSet rs = db.get("Orders");
        ArrayList<Order> orders = new ArrayList<Order>();
        Order aux;
        aux = convertToOrder(rs);
        while(aux != null)
        {
            orders.add(aux);
            aux = convertToOrder(rs);
        }
        int i;
        rs = db.get("OrderItem");
        aux = getRow(rs);
        while(aux != null)
        {
            for(i = 0; i < orders.size(); ++i)
            {
                if(orders.get(i).getTableId().equals(aux.getTableId()))
                {
                    orders.get(i).addItem(aux.getItemsWithQuantity().get(0));
                    break;
                }
            }
            aux = getRow(rs);
        }
        return orders;
    }

    public void remove(String orderId)
    {
        db.remove("OrderItem", "tableid = '" + orderId + "'");
        db.remove("Orders", "tableid = '" + orderId + "'");
    }

    public void removeAll()
    {
        db.removeAll("OrderItem");
        db.removeAll("Orders");
    }

    public void changeState(String tableId, String itemId, ItemState state)
    {
        Order o = readOrder(tableId);
        o.getItemWithQuantity(itemId).changeState(state);
        remove(tableId);
        addOrder(o);
    }

    public void addToOrder(Order order)
    {
        Order o = readOrder(order.getTableId());

        if(!(order.getNote() == null) && !order.getNote().equals("") && !order.getNote().equals(o.getNote()))
            o.setNote(order.getNote());

        int k = order.getNumberOfItems();
        for(int i = 0; i < k; ++i)
        {
            o.addItem(order.getItemWithQuantity(i));
        }

        remove(o.getTableId());
        addOrder(o);
    }
}
