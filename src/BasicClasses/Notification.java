package basicClasses;

import java.io.Serializable;

public class Notification implements Serializable {
    private String notificationText;
    private Order order;

    public Notification(String notificationText, Order order){
        this.notificationText = notificationText;
        this.order = order;
    }

    public Notification(String notificationText){
        this.notificationText = notificationText;
    }

    public String getNotificationText(){
        return notificationText;
    }

    public Order getOrder(){
        return order;
    }

    public String getTableId(){
        return order.getTableId();
    }

    public int getNumberOfItemsInOrder(){
        return order.getNumberOfItems();
    }

    public int getPrice(){
        int price = 0;
        for (int i = 0; i < order.getNumberOfItems(); i++){
            price+= order.getItems().get(i).getPrice();
        }
        return price;
    }
}
