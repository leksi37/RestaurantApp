package client.viewModel.customer;

import BasicClasses.ItemQuantity;
import BasicClasses.Order;
import client.model.customer.CustomerModel;
import client.networking.Client;
import client.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderItemsListViewModel {
    private CustomerModel model;
    private ViewHandler viewHandler;
    private StringProperty note;
    private ObservableList<ItemQuantity> items = FXCollections.observableArrayList();

    public OrderItemsListViewModel(CustomerModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        note = new SimpleStringProperty();
        model.addListeners("orderChanged", this :: orderChanged);
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

    public void sendOrder(Order order) {
        model.addOrderToServer(order);
    }

    public void sendToFrontMenu() {
        viewHandler.openMenuFront();
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
