package tests;

import JDBC.MenuItemsReader;
import JDBC.MenuReader;
import basicClasses.CategoryType;
import basicClasses.MenuItem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class MenuItemsReaderTest {
    private MenuReader reader = new MenuItemsReader();

    @Test
    public void testGetById()
    {
        MenuItem item = reader.getById("d1");
        System.out.println(item);
        assertTrue(item.getName().equals("lemonade"));
    }

    @Test
    public void testGet()
    {
        MenuItem item = reader.getByName("lemonade");
        System.out.println(item);
        assertTrue(item.getId().equals("d1"));
    }

    @Test
    public void failTestGet() {
        MenuItem item = reader.getByName("lemade");
        assertTrue(item == null);

    }

    @Test
    public void testRemove()
    {
        reader.remove("d1");
        assertTrue(reader.getById("d1") == null);
    }

    @Test
    public void testAdd()
    {
        MenuItem i1 = new MenuItem("d1", "lemonade", "water + lemon + sugar", CategoryType.nonAlcoholic, 10);
        MenuItem i2 = new MenuItem("d200", "liquor", "nice alcohol", CategoryType.alcohol, 30);
        MenuItem i3 = new MenuItem("f100", "pancakes", "yummy", CategoryType.dessert, 40);
        MenuItem i4 = new MenuItem("f201", "chicken soup", "chicken water", CategoryType.soup, 20);
        reader.add(i1);
        reader.add(i2);
        reader.add(i3);
        reader.add(i4);
        i1 = null;
        i1 = reader.getById("d1");
        assertTrue(i1.getName().equals("lemonade"));
        i2 = null;
        i2 = reader.getById("d1");
        assertTrue(i2.getName().equals("lemonade"));
        i3 = null;
        i3 = reader.getById("d1");
        assertTrue(i3.getName().equals("lemonade"));
        i4 = null;
        i4 = reader.getById("d1");
        assertTrue(i4.getName().equals("lemonade"));
    }

    @Test
    public void change()
    {
        MenuItem i = new MenuItem("d203", "lala", "lalaaaaa", CategoryType.soup, 12);
        i.setName("LALAA");
        reader.change(i);
        assertTrue(reader.getById("d203").getName().equals("LALAA"));
    }
}