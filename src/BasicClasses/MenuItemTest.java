package basicClasses;

import JDBC.MenuItemsReader;
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

    @Test
    public void testItemQUantitygetItem()
    {
        ItemQuantity iq = new ItemQuantity("d2", 4);
        System.out.println(iq.getItem());
    }
}