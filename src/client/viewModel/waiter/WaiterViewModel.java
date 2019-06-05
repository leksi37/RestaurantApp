package client.viewModel.waiter;

import basicClasses.ItemQuantity;
import basicClasses.ItemState;
import basicClasses.Notification;
import basicClasses.Order;
import client.model.waiter.WaiterModel;
import client.view.ViewHandler;
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
    private ObservableList<String> notificationList = FXCollections.observableArrayList();
    private ObservableList<String> details = FXCollections.observableArrayList();
    private WaiterModel waiterModel;
    PropertyChangeSupport support;
    private ViewHandler viewHandler;

    public WaiterViewModel(WaiterModel waiterModel, ViewHandler vh){
        this.waiterModel = waiterModel;
        this.viewHandler=vh;
        support = new PropertyChangeSupport(this);
        notifications = new SimpleListProperty<>();
        notifications.set(notificationList);
        waiterModel.addListeners("customerRequest", this :: notificationReceived);
        waiterModel.addListeners("partial", this :: partial);
        waiterModel.addListeners("chefRequest", this :: chefRequest);
        waiterModel.addListeners("Receipt request", this::receiptRequest);
        waiterModel.addListeners("orderClosed", this::orderClosed);
    }

    private void orderClosed(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() ->
                refreshDetails((String)propertyChangeEvent.getNewValue())
        );
    }

    public void addListeners(String name, PropertyChangeListener listener) {
        if (name == null)
            support.addPropertyChangeListener(listener);
        else support.addPropertyChangeListener(name, listener);
    }

    private void receiptRequest(PropertyChangeEvent changeEvent) {
        Platform.runLater(() ->
            notifications.add(0, ((Notification)changeEvent.getNewValue()).getNotificationText())
                );
    }

    private void chefRequest(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() ->
                notifications.add(0, ((Notification)propertyChangeEvent.getNewValue()).getNotificationText())
        );
    }

    private void partial(PropertyChangeEvent propertyChangeEvent) {
        Notification n = (Notification)propertyChangeEvent.getNewValue();
        int j = Character.getNumericValue(((Order)n.getObject()).getTableId().charAt(5));
        ArrayList<ItemQuantity> items= ((Order)n.getObject()).getItemsWithQuantity();
        Platform.runLater(() ->{
                notifications.add(0, n.getNotificationText());
                refreshDetails(((Order)n.getObject()).getTableId());
                 });
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
                        notifications.add(0, n.getNotificationText())
                );
        int tableNum = Character.getNumericValue(((String)n.getObject()).charAt(5));
    }

    public ListProperty<String> getNotifications() {
        return notifications;
    }

    public ObservableList<String> getDetails() {
        return details;
    }

    public void requestCloseOrder(int num){

    }

    public void requestClose(int lastSelectedTable) {
        waiterModel.closeOrder(lastSelectedTable);
    }
}
