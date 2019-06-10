package tests;

import basicClasses.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    Order o = new Order("id1");

    @Test
    public void addItems()
    {
        o.addItem(new ItemQuantity(new MenuItem("d2", "lemonade", "fancy description", CategoryType.nonAlcoholic, 15), 15));
        assertTrue(o.getItemWithQuantity("d2", ItemState.notStarted).getItem().getName().equals("lemonade"));
    }

    @Test
    public void testConstructorAndEquals()
    {
        Order o2 = new Order("id2");
        o2.addItem(new ItemQuantity(new MenuItem("d2", "lemonade", "fancy description", CategoryType.nonAlcoholic, 15), 15));
        o2.getItemWithQuantity("d2", ItemState.notStarted).changeState(ItemState.toWaiter);
        o = new Order(o2);
        assertTrue(o.equals(o2));
    }

    @Test
    public void testOtherConstructorAndEquals()
    {
        Order o2 = new Order(o);
        assertTrue(o2.equals(o));
    }

    @Test
    public void testAddToOrderAndNumberOfItems()
    {
        o.addItem(new ItemQuantity(new MenuItem("d2", "lemonade",
                "fancy description", CategoryType.nonAlcoholic, 15), 15));
        Order o2 = new Order("id1");
        o2.addItem(new ItemQuantity(new MenuItem("d5", "coffee",
                "fancy description", CategoryType.nonAlcoholic, 15), 15));
        o.addToOrder(o2);
        assertTrue(o.getNumberOfItems()==2);
    }

    @Test
    public void testNote()
    {
        o.setNote("tra la la");
        assertTrue(o.getNote().equals("tra la la"));
    }

    @Test
    public void testPrice()
    {
        o.addItem(new ItemQuantity(new MenuItem("d2", "lemonade", "fancy description", CategoryType.nonAlcoholic, 15), 15));
        o.addItem(new ItemQuantity(new MenuItem("d5", "coffee", "fancy description", CategoryType.nonAlcoholic, 10), 15));
        assertTrue(o.getPrice() == 375);
    }

    @Test
    public void addSameItemDifferentStates()
    {
        o.addItem(new ItemQuantity(new MenuItem("d2", "lemonade", "fancy description", CategoryType.nonAlcoholic, 15), 15));
        o.addItem(new ItemQuantity(new MenuItem("d2", "lemonade", "fancy description", CategoryType.nonAlcoholic, 15), 15, ItemState.toWaiter));
        assertTrue(o.getItemsWithQuantity().size() == 2);
    }

    @Test
    public void addSame()
    {
        o.addItem(new ItemQuantity(new MenuItem("d2", "lemonade", "fancy description", CategoryType.nonAlcoholic, 15), 15));
        o.addItem(new ItemQuantity(new MenuItem("d2", "lemonade", "fancy description", CategoryType.nonAlcoholic, 15), 15));
        assertTrue(o.getItemsWithQuantity().size() == 1);
        assertTrue(o.getItemWithQuantity("d2", ItemState.notStarted).getQuantity() == 30);
    }
}