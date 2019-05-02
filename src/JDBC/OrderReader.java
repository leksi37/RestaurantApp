package JDBC;

import BasicClasses.ItemQuantity;
import BasicClasses.ItemState;
import BasicClasses.Order;

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
        ArrayList<String> orderItems = order.dbFormat();
        for(int i = 0; i < orderItems.size(); ++i)
        {
            db.insert("orders", orderItems.get(i));
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

    public Order readOrder(String tableId)
    {
        ResultSet rs = db.get("orders", "tableid = '" + tableId + "'");
        Order o = new Order(tableId);
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
        ResultSet rs = db.get("orders");
        ArrayList<Order> orders = new ArrayList<Order>();
        Order aux;
        aux = getRow(rs);
        int i;
        while(aux != null)
        {
            i = 0;
            for(i = 0; i < orders.size(); ++i)
            {
                if(orders.get(i).getTableId().equals(aux.getTableId()))
                {
                    orders.get(i).addItem(aux.getItemsWithQuantity().get(0));
                    break;
                }
            }
            if(i == orders.size())
            {
                orders.add(aux);
            }
            aux = getRow(rs);
        }
        return orders;
    }

    public void remove(String orderId)
    {
        db.remove("orders", "tableid = '" + orderId + "'");
    }

    public void removeAll()
    {
        db.removeAll("orders");
    }

    public void changeState(String tableId, String itemId, ItemState state)
    {
        Order o = readOrder(tableId);
        o.getItemWithQuantityById(itemId).changeState(state);
        remove(o.getTableId());
        addOrder(o);
    }

    public void addToOrder(Order order)
    {
        Order o = readOrder(order.getTableId());
        remove(o.getTableId());
        for(int i = 0; i < order.getItemsWithQuantity().size(); ++i)
        {
            o.addItem(order.getItemsWithQuantity().get(i));
        }
        addOrder(o);
    }
}
