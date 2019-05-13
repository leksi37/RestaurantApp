package BasicClasses;

import JDBC.MenuItemsReader;

import java.io.Serializable;

public class ItemQuantity implements Serializable {
    private String id;
    private int quantity;
    private ItemState state;

    public ItemQuantity(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
        state = ItemState.notStarted;
    }

    public ItemQuantity(String id, int quantity, ItemState state) {
        this.id = id;
        this.quantity = quantity;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public MenuItem getItem()
    {
        MenuItemsReader reader = MenuItemsReader.getInstance();
        return reader.getById(id);
    }

    public void changeState(ItemState state)
    {
        this.state = state;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString()
    {
        MenuItem item = getItem();
        return item.getName() + ", " + quantity + " pieces, " + item.getPrice() + "kr";
    }

    public String dbFormat() {
        return "'" + id + "', '" + quantity + "', '" + state.name() + "'";
    }
}
