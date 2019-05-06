package client.viewModel.customer;

import BasicClasses.ItemQuantity;
import BasicClasses.Order;
import client.model.customer.CustomerModel;
import client.viewModel.ViewModels;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class OrderItemsListViewModel implements ViewModels {
    private CustomerModel model;
    private StringProperty note;
    private ObservableList<ItemQuantity> items = FXCollections.observableArrayList();
    PropertyChangeSupport support;

    public OrderItemsListViewModel(CustomerModel model) {
        this.model = model;
        support = new PropertyChangeSupport(this);
        note = new SimpleStringProperty();
        model.addListeners("orderChanged", this :: orderChanged);
        model.addListeners("orderAdded", this :: orderAdded);
    }

    //If we can call PropertyChangeSupport in the view then we can leave it if not this should be removed! and every submethod also
    private void orderAdded(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange("orderAddedForChange", null, null);
    }

    private void orderChanged(PropertyChangeEvent propertyChangeEvent) {
        setOrderItems();
    }

    public void setOrderItems()
    {
        Order order = model.getOrder();
        items.clear();
        for(int i = 0; i < order.getItemsWithQuantity().size(); i++){
            items.add(order.getItemsWithQuantity().get(i));
        }
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void sendOrder() {
        model.addOrderToServer();
    }

    public ObservableList<ItemQuantity> getItems() {
        return items;
    }

    public void remove(Object focusedItem) {
        model.removeItem((ItemQuantity) focusedItem);
    }

}
