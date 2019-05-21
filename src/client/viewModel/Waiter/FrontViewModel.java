package client.viewModel.Waiter;

import basicClasses.Notification;
import client.model.waiter.WaiterModel;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class FrontViewModel {
    private ObservableList<Notification> notifications = FXCollections.observableArrayList();
    private WaiterModel waiterModel;
    PropertyChangeSupport support;

    public FrontViewModel(WaiterModel waiterModel){
        this.waiterModel = waiterModel;
        support = new PropertyChangeSupport(this);
        waiterModel.addListeners("Notification received", this :: notificationReceived);
    }

    public void notificationReceived(PropertyChangeEvent event){
        notifications.add((Notification)event.getNewValue());
    }

    public ListProperty<Notification> getNotifications() {
        return (ListProperty<Notification>) notifications;
    }
}
