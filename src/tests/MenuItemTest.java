package tests;

import JDBC.MenuItemsReader;
import basicClasses.ItemQuantity;
import basicClasses.MenuItem;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MenuItemTest {
    @Test
    public void testEquals()
    {
        MenuItemsReader r = MenuItemsReader.getInstance();
        MenuItem i1 = r.getByName("lemonade");
        MenuItem i2 = r.getByName("lemonade");
        assertTrue(i1.equals(i2));
    }
}