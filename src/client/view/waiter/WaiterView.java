package client.view.waiter;

import client.view.ViewHandler;
import client.viewModel.Waiter.FrontViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class WaiterView {
    private FrontViewModel viewModel;
    private ObservableList<String> orderItems = FXCollections.observableArrayList();
    private ViewHandler viewHandler;

    public WaiterView(ViewHandler viewHandler){
    this.viewHandler = viewHandler;
    }

    @FXML
    private Button one;

    @FXML
    private ListView notifications;

    @FXML
    private Label update;

    public void init(FrontViewModel viewModel){
        this.viewModel = viewModel;
    }

    public void getOrderDetails(){
        update.setText("An order is ready to be delivered:");
        notifications.setItems(orderItems);
    }

}
