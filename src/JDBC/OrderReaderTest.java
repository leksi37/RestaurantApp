package JDBC;

import BasicClasses.ItemQuantity;
import BasicClasses.ItemState;
import BasicClasses.MenuItem;
import BasicClasses.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OrderReaderTest {
    private OrderReader reader;
    private MenuItemsReader menuItemsReader;
    @Before
    public void setup()
    {
        reader = OrderReader.getInstance();
        menuItemsReader = MenuItemsReader.getInstance();
    }
//        reader.removeAll();
//        Order o = new Order("table1");
//        MenuItem i1 = menuItemsReader.getByName("lemonade");
//        MenuItem i2 = menuItemsReader.getByName("liquor");
//        MenuItem i3 = menuItemsReader.getByName("sprite");
//        MenuItem i4 = menuItemsReader.getByName("LALAA");
//        o.addItem(i1.getId());
//        o.addItem(i4.getId());
//        o.addItem(i3.getId());
//        o.addItem(i2.getId());
//        o.addItem(i2.getId());
//        reader.addOrder(o);
//        o = new Order("table2");
//        o.addItem(menuItemsReader.getById("d12").getId());
//        o.addItem(menuItemsReader.getById("d4").getId());
//        reader.addOrder(o);
//    }

    @Test
    public void testAddOrder()
    {
        Order o = new Order("table5");
        MenuItem i1 = menuItemsReader.getById("d5");
        o.addItem(i1.getId());
        reader.addOrder(o);
    }

    @Test
    public void testRemoveAll()
    {
        reader.removeAll();
    }

    @Test
    public void testRemove()
    {
        reader.remove("table1");
    }

    @Test
    public void testChangeState()
    {
        reader.changeState("table1", "d1", ItemState.delivered);
    }

    @Test
    public void testAddToOrder()
    {
        Order o = new Order("table2");
        o.addItem(new ItemQuantity(menuItemsReader.getById("d2").getId(), 2));
        o.addItem(new ItemQuantity(menuItemsReader.getById("d3").getId(), 2));
        o.addItem(menuItemsReader.getById("f2").getId());
        reader.addToOrder(o);
    }

    @After
    public void showOrders()
    {
        ArrayList<Order> orders = reader.readAllOrders();
        for(int i = 0; i < orders.size(); ++i)
        {
            System.out.println(orders.get(i));
        }
    }

    @Test
    public void testSingleton()
    {
        OrderReader r1 = OrderReader.getInstance();
        MenuItemsReader r2 = MenuItemsReader.getInstance();
    }
}