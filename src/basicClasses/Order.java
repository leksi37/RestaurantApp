package basicClasses;

import JDBC.MenuItemsReader;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String tableId;
    private ArrayList<ItemQuantity> items;
    private String note;

    public Order(String tableId) {
        this.tableId = tableId;
        items = new ArrayList<ItemQuantity>();
        note = "";
    }

    public Order(Order order)
    {
        items = new ArrayList<ItemQuantity>();
        if(order.note != null && !order.note.equals("null"))
            note = order.note;
        if(order != null)
        {
            tableId = new String(order.tableId);
            if(order.items != null)
            {
                for(int i = 0; i < order.items.size(); ++i)
                {
                    items.add(new ItemQuantity(order.items.get(i).getItem(), order.items.get(i).getQuantity()));
                    items.get(i).changeState(order.items.get(i).getState());
                }
            }
        }
    }

    private int isInOrder(ItemQuantity item)
    {
        for(int i = 0; i < items.size(); ++i) {
            if (items.get(i).getId().equals(item.getId()) && items.get(i).getState().equals(item.getState()))
                return i;
        }
        return -1;
    }

    public void addItem(ItemQuantity itemQ)
    {
        int k = isInOrder(itemQ);
        if(k == -1)
            items.add(itemQ);
        else items.get(k).setQuantity(items.get(k).getQuantity() + itemQ.getQuantity());
    }

    public String getTableId() {
        return tableId;
    }

    public ArrayList<ItemQuantity> getItemsWithQuantity() {
        return items;
    }

    public ArrayList<String> dbFormatItems()
    {
        ArrayList<String> strings = new ArrayList<String>();
        String s;
        for(int i = 0; i < items.size(); ++i)
        {
            s = "'" + tableId + "', " + items.get(i).dbFormat();
            strings.add(s);
        }
        return strings;
    }

    public String toString()
    {
        String s = tableId + ", " + note + "\n";
        if(items.size() > 0)
        {
            s += "(";
            for(int i = 0; i < items.size()-1; ++i)
            {
                s += "item: " + items.get(i).orderDisplay() + "\n";
            }
            s += "item: " + items.get(items.size()-1).orderDisplay() + ")";
        }
        return s;
    }

    public ItemQuantity getItemWithQuantity(String id, ItemState state)
    {
        for(int i = 0; i < items.size(); ++i)
        {
            if(items.get(i).getId().equals(id) && items.get(i).getState() == state)
                return items.get(i);
        }
        return null;
    }

    public ItemQuantity getItemWithQuantity(int index)
    {
        if(index < items.size())
            return items.get(index);
        return null;
    }

    public void removeItem(String id, int quantity)
    {
        for(int i = 0; i < items.size(); ++i)
        {
            if(items.get(i).getId().equals(id))
                items.remove(i);
        }
    }

    public String dbFormat()
    {
        return "'" + tableId + "', '" + note + "'";
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNumberOfItems()
    {
        return items.size();
    }

    public void addToOrder(Order order) {

        if(order.note != null)
            if (note == null || note.equals(""))
                note = order.note;
            else
                note = note + "\n" + order.note;
        for (int i = 0; i < order.items.size(); ++i)
        {
            addItem(order.items.get(i));
        }
        checkStates();
    }

    public boolean isReadyToBeClosed()
    {
        for(int i = 0; i < items.size(); ++i)
        {
            if(items.get(i).getState() != ItemState.toWaiter)
                return false;
        }
        return true;
    }

    public void clearItems() {
        items = new ArrayList<ItemQuantity>();
    }

    public double getPrice() {
        int price = 0;
        for(int i = 0; i < items.size(); ++i)
        {
            price += items.get(i).getQuantity()*items.get(i).getItem().getPrice();
        }
        return price;
    }

    public boolean equals(Object obj)
    {
        if(obj == null || !(obj instanceof Order)) return false;
        Order o = (Order)obj;
        if(!(note.equals(o.note) && tableId.equals(o.tableId) && items.size() == o.items.size()))
        {
            return false;
        }
        for(int i = 0; i < items.size(); ++i)
        {
            if(!items.get(i).equals(o.items.get(i)))
                return false;
        }
        return true;
    }

    public void checkStates() {
        for(int i = 0; i < items.size()-1; ++i)
            for(int j = i+1; j < items.size(); ++j)
            {
                if(items.get(i).getId().equals(items.get(j).getId()) && items.get(i).getState() == items.get(j).getState())
                {
                    items.get(i).setQuantity(items.get(i).getQuantity() + items.get(j).getQuantity());
                    items.remove(j);
                }
            }
    }

    public boolean canBeSent()
    {
        for(int i = 0; i < items.size(); ++i)
        {
            if(items.get(i).getState() == ItemState.forWaiter)
                return true;
        }
        return false;
    }
}