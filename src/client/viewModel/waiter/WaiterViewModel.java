package client.viewModel.waiter;

import basicClasses.ItemQuantity;
import basicClasses.ItemState;
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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


public class WaiterViewModel implements ViewModels {
    private ListProperty<String> notifications;
    //private ListProperty<String> details;
    private ObservableList<String> notificationList = FXCollections.observableArrayList();
    private ObservableList<String> details = FXCollections.observableArrayList();
    private WaiterModel waiterModel;
    PropertyChangeSupport support;

    public WaiterViewModel(WaiterModel waiterModel){
        this.waiterModel = waiterModel;
        support = new PropertyChangeSupport(this);
        notifications = new SimpleListProperty<>();
        notifications.set(notificationList);
        //details=new SimpleListProperty<>();
        //details.set(detailsList);
        waiterModel.addListeners("customerRequest", this :: notificationReceived);
        waiterModel.addListeners("partial", this :: partial);
        waiterModel.addListeners("chefRequest", this :: chefRequest);
        waiterModel.addListeners("Receipt request", this::receiptRequest);
    }

    public void addListeners(String name, PropertyChangeListener listener) {
        if (name == null)
            support.addPropertyChangeListener(listener);
        else support.addPropertyChangeListener(name, listener);
    }

    private void receiptRequest(PropertyChangeEvent changeEvent) {
        Platform.runLater(() ->
            notifications.add(((Notification)changeEvent.getNewValue()).getNotificationText())
                );
    }

    private void chefRequest(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() ->
                notifications.add(((Notification)propertyChangeEvent.getNewValue()).getNotificationText())
        );
    }

    private void partial(PropertyChangeEvent propertyChangeEvent) {
        Notification n = (Notification)propertyChangeEvent.getNewValue();
        int j = Character.getNumericValue(((Order)n.getObject()).getTableId().charAt(5));
        ArrayList<ItemQuantity> items= ((Order)n.getObject()).getItemsWithQuantity();
        Platform.runLater(() ->{
                notifications.add(n.getNotificationText());
                refreshDetails(((Order)n.getObject()).getTableId());
                 });
        support.firePropertyChange("Notification for waiter", null, j);
    }

    public void refreshDetails(String id){
        details.clear();
        Order o =waiterModel.getOrder(id);
        if(o!=null) {
            ArrayList<ItemQuantity> items = o.getItemsWithQuantity();
                Platform.runLater(() -> {
                    for (int i = 0; i < items.size(); i++)
                        details.add(items.get(i).toStringWaiter());
                });
        }
    }

    public boolean readyToClose(String id){
        boolean t=true;
        Order o =waiterModel.getOrder(id);
        if(o!=null) {
            ArrayList<ItemQuantity> items = o.getItemsWithQuantity();
            for(int i=0;i<items.size();i++)
                if(!items.get(i).getState().equals(ItemState.toWaiter))
                    t=false;
        }
        return t;
    }

    public void notificationReceived(PropertyChangeEvent event){
        Notification n = (Notification)event.getNewValue();
        Platform.runLater(() ->
                        notifications.add(n.getNotificationText())
                );
        int tableNum = Character.getNumericValue(((String)n.getObject()).charAt(5));
        support.firePropertyChange("Notification for waiter", null, tableNum);
    }

    public ListProperty<String> getNotifications() {
        return notifications;
    }

    public ObservableList<String> getDetails() {
        return details;
    }

    public void requestCloseOrder(int num){

    }
}