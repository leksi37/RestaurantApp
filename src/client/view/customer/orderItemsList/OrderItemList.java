package client.view.customer.orderItemsList;


import basicClasses.Views;
import client.view.ViewHandler;
import client.viewModel.customer.OrderItemsListViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;



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
        oilvm.remove(orderItems.getFocusModel().getFocusedItem());
    }

    public void backToMenu() {
        Platform.runLater(() -> {
            viewHandler.openView(Views.CATEGORIES);
        });
    }
}
