package client.viewModel.waiter;

import basicClasses.Notification;
import client.model.waiter.WaiterModel;
import client.viewModel.ViewModels;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;


public class WaiterViewModel implements ViewModels {
    private ListProperty<String> notifications;
    private ObservableList<String> notificationList = FXCollections.observableArrayList();
    private WaiterModel waiterModel;
    PropertyChangeSupport support;

    public WaiterViewModel(WaiterModel waiterModel){
        this.waiterModel = waiterModel;
        support = new PropertyChangeSupport(this);
        notifications = new SimpleListProperty<>();
        notifications.set(notificationList);
        waiterModel.addListeners("Notification received", this :: notificationReceived);
    }

    public void notificationReceived(PropertyChangeEvent event){
        notifications.add("Presence requested at " + ((Notification)event.getNewValue()).getNotificationText());
        int tableNum = event.getOldValue().toString().charAt(event.getOldValue().toString().toCharArray().length-1);

        System.out.println( event.getOldValue().toString().charAt(event.getOldValue().toString().toCharArray().length-1));
        support.firePropertyChange("Notification for waiter", null, tableNum);
    }

    public ListProperty<String> getNotifications() {
        return notifications;
    }
}
