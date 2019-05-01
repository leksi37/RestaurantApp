package client.view.customer.orderItemsList;

import BasicClasses.ItemQuantity;
import BasicClasses.MenuItem;
import BasicClasses.Order;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.OrderItemsListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


public class OrderItemList {
    private OrderItemsListViewModel oilvm;

    @FXML
    ListView orderItems;

    @FXML
    TextArea note;

    @FXML
    public void placeOrder(){
//        oilvm.sendOrder();
//        oilvm.sendToFrontMenu();
    }

    public void init(ViewModelProvider viewModelProvider) {
        note.textProperty().bindBidirectional(oilvm.noteProperty());
    }



    @FXML
    public void removeSelected(){
//        deliverItem((ItemQuantity) orderItems.getFocusModel().getFocusedItem());
    }
}
