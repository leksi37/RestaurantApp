package client.view.customer.orderItemsList;

import basicClasses.ItemQuantity;
import basicClasses.MenuItem;
import basicClasses.Order;
import basicClasses.Views;
import client.view.ViewHandler;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.OrderItemsListViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    Label priceLabel;

    @FXML
    TextArea note;

    public void placeOrder(){
        Platform.runLater(() -> {
            oilvm.sendOrder();
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
        priceLabel.setText("" + oilvm.getPriceOfOrder());

    }

    @FXML
    public void removeSelected(){
        oilvm.remove(orderItems.getSelectionModel().getSelectedIndex(), (ItemQuantity) orderItems.getFocusModel().getFocusedItem());
        priceLabel.setText("" + oilvm.getPriceOfOrder()); //change price when removed
        System.out.println("Is price changing correctly???");
    }

    public void backToMenu() {
        Platform.runLater(() -> {
            viewHandler.openView(Views.CATEGORIES);
        });
    }
}
