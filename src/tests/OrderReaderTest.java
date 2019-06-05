package tests;

import JDBC.MenuItemsReader;
import JDBC.MenuReader;
import JDBC.OrderReader;
import JDBC.OrdersReader;
import basicClasses.ItemQuantity;
import basicClasses.ItemState;
import basicClasses.MenuItem;
import basicClasses.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class OrderReaderTest {
    private OrdersReader reader;
    private MenuReader menuItemsReader;
    @Before
    public void setup()
    {
        reader = new OrderReader();
        menuItemsReader = new MenuItemsReader();
    }

    @Test
    public void testAddOrder()
    {
        Order o = new Order("table4");
        MenuItem i1 = menuItemsReader.getById("f11");
        MenuItem i2 = menuItemsReader.getById("d2");
        ItemQuantity iq1 = new ItemQuantity(i1, 5);
        iq1.changeState(ItemState.done);
        ItemQuantity iq2 = new ItemQuantity(i2, 5);
        iq2.changeState(ItemState.toWaiter);
        o.setNote("Tra la la nota mea");
        o.addItem(iq1);
        o.addItem(iq2);
        reader.addOrder(o);
        Order o2 = reader.readOrder("table4");
        assertTrue(o2.equals(o));
    }

    @Test
    public void testRemove()
    {
        reader.remove("table4");
        assertTrue(reader.readOrder("table4") == null);
    }
}