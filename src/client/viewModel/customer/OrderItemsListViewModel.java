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

public class OrderItemsListViewModel {
    private CustomerModel model;
    private ViewHandler viewHandler;
    private StringProperty note;
    private ObservableList<ItemQuantity> items = FXCollections.observableArrayList();

    public OrderItemsListViewModel(CustomerModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        note = new SimpleStringProperty();
        Order order = model.getOrder();
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
        //to be changed with status
        viewHandler.openMenuFront();
    }
}
