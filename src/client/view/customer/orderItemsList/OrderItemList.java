package client.view.customer.orderItemsList;

import BasicClasses.ItemQuantity;
import BasicClasses.MenuItem;
import BasicClasses.Order;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.OrderItemsListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


public class OrderItemList {
    private OrderItemsListViewModel oilvm;

    @FXML
    ListView orderItems;

    @FXML
    TextArea note;

    public void placeOrder(){
        oilvm.sendOrder();
    }

    public void init(OrderItemsListViewModel o)
    {
        oilvm = o;
        oilvm.setOrderItems();
        orderItems.setItems(oilvm.getItems());
        note.textProperty().bindBidirectional(oilvm.noteProperty());
    }



    @FXML
    public void removeSelected(){
        oilvm.remove(orderItems.getFocusModel().getFocusedItem());
    }

    public void backToMenu() {
        oilvm.backToMenu();
    }
}
