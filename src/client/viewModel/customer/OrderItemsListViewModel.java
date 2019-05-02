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
    private ObservableList<String> items = FXCollections.observableArrayList();

    public OrderItemsListViewModel(CustomerModel model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        note = new SimpleStringProperty();
    }

    public void setOrderItems()
    {
        Order order = model.getOrder();
        String item;
        for(int i = 0; i < order.getItemsWithQuantity().size(); i++){
            item = order.getItemsWithQuantity().get(i).orderDisplay();
            items.add(order.getItemsWithQuantity().get(i).orderDisplay());
            System.out.println(item);
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

    public ObservableList<String> getItems() {
        return items;
    }
}
