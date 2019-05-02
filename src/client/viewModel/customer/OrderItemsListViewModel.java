package client.viewModel.customer;

import BasicClasses.ItemQuantity;
import BasicClasses.Order;
import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.networking.Client;
import client.view.ViewHandler;
import client.viewModel.ViewModels;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderItemsListViewModel implements ViewModels {
    private CustomerModel model;
    private ViewHandler viewHandler;
    private StringProperty note;
    private ObservableList<ItemQuantity> items = FXCollections.observableArrayList();

    public OrderItemsListViewModel(CustomerModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        note = new SimpleStringProperty();
        model.addListeners("orderChanged", this :: orderChanged);
        model.addListeners("orderAdded", this :: orderAdded);
    }

    private void orderAdded(PropertyChangeEvent propertyChangeEvent) {
        viewHandler.openView(Views.MENU_FRONT);
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

    public void sendToFrontMenu() {
        viewHandler.openView(Views.MENU_FRONT);
    }

    public ObservableList<ItemQuantity> getItems() {
        return items;
    }

    public void remove(Object focusedItem) {
        model.removeItem((ItemQuantity) focusedItem);
    }

    public void backToMenu() {
        viewHandler.openCategoryList();
    }
}
