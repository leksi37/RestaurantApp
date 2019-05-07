package client.view.customer.orderItemsList;

import BasicClasses.ItemQuantity;
import BasicClasses.MenuItem;
import BasicClasses.Order;
import BasicClasses.Views;
import client.view.ViewHandler;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.OrderItemsListViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import javax.swing.text.View;
import java.awt.image.VolatileImage;


public class OrderItemList {
    private OrderItemsListViewModel oilvm;
    private ViewHandler viewHandler;

    @FXML
    ListView orderItems;

    @FXML
    TextArea note;

    public void placeOrder(){
        Platform.runLater(() -> {
            viewHandler.openView(Views.MENU_FRONT_LABEL);
        });
    }

    public void init(OrderItemsListViewModel o, ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
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
        Platform.runLater(() -> {
            viewHandler.openView(Views.CATEGORIES);
        });
    }
}
