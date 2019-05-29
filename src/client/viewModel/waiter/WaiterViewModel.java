package client.viewModel.waiter;

import basicClasses.Notification;
import basicClasses.Order;
import client.model.waiter.WaiterModel;
import client.viewModel.ViewModels;
import javafx.application.Platform;
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
        waiterModel.addListeners("customerRequest", this :: notificationReceived);
        waiterModel.addListeners("partial", this :: partial);
        waiterModel.addListeners("chefRequest", this :: chefRequest);
    }

    private void chefRequest(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() ->
                notifications.add(((Notification)propertyChangeEvent.getNewValue()).getNotificationText())
        );
    }

    private void partial(PropertyChangeEvent propertyChangeEvent) {
        Notification n = (Notification)propertyChangeEvent.getNewValue();
        int i = Character.getNumericValue(((Order)n.getObject()).getTableId().charAt(5))+1;
        Platform.runLater(() ->
                notifications.add(n.getNotificationText())
                );
        support.firePropertyChange("Notification for waiter", null, i);
    }

    public void notificationReceived(PropertyChangeEvent event){
        Notification n = (Notification)event.getNewValue();
        notifications.add(n.getNotificationText());
        int tableNum = Character.getNumericValue(((String)n.getObject()).charAt(5));
        support.firePropertyChange("Notification for waiter", null, tableNum);
    }

    public ListProperty<String> getNotifications() {
        return notifications;
    }
}
