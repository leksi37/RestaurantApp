package client.viewModel.customer;

import basicClasses.ItemQuantity;
import basicClasses.Order;
import basicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;
import javafx.application.Platform;
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
    private ViewHandler viewHandler;

    public OrderItemsListViewModel(CustomerModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler=viewHandler;
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
        model.addOrderToServer(note.getValue());
        items.clear();
        Platform.runLater(() -> {
            viewHandler.openView(Views.MENU_FRONT_LABEL);
        });
    }

    public ObservableList<ItemQuantity> getItems() {
        return items;
    }


    public void remove(int index, ItemQuantity focusedItem) {
        if(index != -1)
            model.removeItem(focusedItem);
    }

    public String getPriceOfOrder() {
        return model.getPrice();
    }

    public void remove(Object focusedItem) {
            model.removeItem((ItemQuantity) focusedItem);
    }

    public void back(){
        Platform.runLater(() -> {
            viewHandler.openView(Views.CATEGORIES);
        });
    }
}
