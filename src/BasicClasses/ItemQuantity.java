package basicClasses;

import JDBC.MenuItemsReader;

import java.io.Serializable;

public class ItemQuantity implements Serializable {
    private String id;
    private int quantity;
    private ItemState state;
    private MenuItem item;

    public ItemQuantity(MenuItem item, int quantity) {
        this.item = item;
        this.id = item.getId();
        this.quantity = quantity;
        state = ItemState.notStarted;
    }

    public ItemQuantity(MenuItem item, int quantity, ItemState state) {
        this.item = item;
        this.id = item.getId();
        this.quantity = quantity;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public MenuItem getItem()
    {
        return item;
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

    public String orderDisplay()
    {
        return "id " + id + ", quantity " + quantity + ", state " + state.name();
    }


    public String toString()
    {
        return item.getName() + ", " + quantity + " pieces, " + item.getPrice() + "kr, state " + state;
    }

    public String dbFormat() {
        return "'" + id + "', '" + quantity + "', '" + state.name() + "'";
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof ItemQuantity))
            return false;
        ItemQuantity q = (ItemQuantity) obj;
        return id.equals(q.id) && quantity == q.quantity && state == q.state;
    }

    public ItemState getState() {
        return state;
    }
}
