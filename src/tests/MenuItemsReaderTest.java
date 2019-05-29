package tests;

import JDBC.MenuItemsReader;
import basicClasses.CategoryType;
import basicClasses.MenuItem;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class MenuItemsReaderTest {
    private MenuItemsReader reader = MenuItemsReader.getInstance();

//    @Before
//    public void setup()
//    {
//        reader.deleteAll();
//        testAdd();
//    }

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
    public void testAdd()
    {
        MenuItem i1 = new MenuItem("d1", "lemonade", "water + lemon + sugar", CategoryType.nonAlcoholic, 10);
        MenuItem i2 = new MenuItem("d2", "liquor", "nice alcohol", CategoryType.alcohol, 30);
        MenuItem i3 = new MenuItem("f1", "pancakes", "yummy", CategoryType.dessert, 40);
        MenuItem i4 = new MenuItem("f2", "chicken soup", "chicken water", CategoryType.soup, 20);
        MenuItem i5 = new MenuItem("d3", "kiwi fresh", "kiwi, banana, milk", CategoryType.nonAlcoholic, 20);
        reader.add(i1);
        reader.add(i2);
        reader.add(i3);
        reader.add(i4);
        reader.add(i5);
        ArrayList<MenuItem> items = reader.getAll();
        assertTrue(items.size() == 5);
    }

    @Test
    public void testGetAll()
    {
        ArrayList<MenuItem> items = reader.getAll();
        for(int i = 0; i < items.size(); ++i)
        {
            System.out.println(items.get(i));
        }
        assertTrue(items.size() == 5);
    }

    @Test
    public void testRemove()
    {
        reader.remove("d1");
        assertTrue(reader.getById("d1") == null);
    }

    @Test
    public void change()
    {
        MenuItem i = new MenuItem("d12", "lala", "mama", CategoryType.soup, 12);
        reader.add(i);
        i.setName("LALAA");
        reader.change(i);
        assertTrue(reader.getById("d12").getName().equals("LALAA"));
    }
}