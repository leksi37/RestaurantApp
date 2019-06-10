package tests;

import basicClasses.CategoryType;
import basicClasses.ItemQuantity;
import basicClasses.ItemState;
import basicClasses.MenuItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemQuantityTest {
    ItemQuantity iq = new ItemQuantity(new MenuItem("d2", "lemonade", "fancy description", CategoryType.nonAlcoholic, 15), 15);

    @Test
    public void testConstructorAndEquals()
    {
        ItemQuantity iq2 = new ItemQuantity(iq.getItem(), 15);
        assertTrue(iq.equals(iq2));
    }

    @Test
    public void testChangeState()
    {
        iq.changeState(ItemState.toWaiter);
        assertTrue(iq.getState() == ItemState.toWaiter);
    }

}