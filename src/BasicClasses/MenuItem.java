package basicClasses;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String id;
    private String name;
    private String description;
    private type type;
    private double price;

    public MenuItem(String id, String name, String description, type type, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public type getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean equals(Object obj)
    {
        if(obj == null || !(obj instanceof MenuItem))
            return false;
        MenuItem i = (MenuItem) obj;
        return (name.equals(i.name) && id.equals(i.id) && description.equals(i.description) && type == i.type && price == i.price);
    }

    public String dbFormat()
    {
        return "'" + id + "', '" + name + "', '" + description + "', '" + type.name() + "', '" + price + "'";
    }

    public String toString()
    {
        return id + ", " + name + ", " + description + ", " + type.name() + ", " + price;
    }
}
