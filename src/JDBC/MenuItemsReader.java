package JDBC;

import BasicClasses.type;
import BasicClasses.MenuItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemsReader {
    private JDBC db;
    private static MenuItemsReader instance;

    private MenuItemsReader() {
        db = JDBC.getInstance();
    }

    public static MenuItemsReader getInstance()
    {
        if(instance == null) {
            instance = new MenuItemsReader();
        }
        return instance;
    }

    public void add(MenuItem item)
    {
        db.insert("Menu", item.dbFormat());
    }

    public void remove(String id)
    {
        db.remove("Menu", "id = '" + id + "'");
    }

    public void deleteAll()
    {
        db.removeAll("Menu");
    }

    public MenuItem getById(String id)
    {
        ResultSet rs = db.get("Menu", "id = '" + id + "'");
        return get(rs);
    }

    public MenuItem getByName(String name)
    {
        ResultSet rs = db.get("Menu", "name = '" + name + "'");
        return get(rs);
    }

    public ArrayList<MenuItem> getCategory(type tp)
    {
        ResultSet rs = db.get("Menu", "type = '" + tp + "'");
        MenuItem i;

        ArrayList<MenuItem> items = new ArrayList<MenuItem>();

        i = get(rs);
        while(i != null)
        {
            items.add(i);
            i = get(rs);
        }
        return items;
    }

    public ArrayList<MenuItem> getAll()
    {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        ArrayList<MenuItem> categoryItems;
        for(BasicClasses.type type : type.values())
        {
            categoryItems = getCategory(type);
            for(int i = 0; i < categoryItems.size(); ++i)
                items.add(categoryItems.get(i));
        }
        return items;
    }

    public void change(MenuItem item)
    {
        db.remove("Menu", "id = '" + item.getId() + "'");
        db.insert("Menu", item.dbFormat());
    }

    private MenuItem get(ResultSet rs)
    {
        try {
            if(rs.next())
                return toMenuItem(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MenuItem toMenuItem(ResultSet rs)
    {
        MenuItem i = null;
        String _id, name, description;
        type t;
        double price;
        try {
            _id = rs.getString("id");
            name = rs.getString("name");
            description = rs.getString("description");
            t = type.valueOf(rs.getString("type"));
            price = rs.getDouble("price");
            i = new MenuItem(_id, name, description, t, price);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return i;
    }
}