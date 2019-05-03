package client.view.waiter.frontView;

import client.viewModel.Waiter.FrontViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class FrontView {
    private FrontViewModel viewModel;
    private ObservableList<String> orderItems = FXCollections.observableArrayList();

    @FXML
    private Button one;

    @FXML
    private ListView notifications;

    @FXML
    private Label update;

    public void init(FrontViewModel viewModel){
        this.viewModel = viewModel;
       // viewModel.addListener("Done order", this::getOrderDetails);
    }

    public void getOrderDetails(){
       // orderItems = (ObservableList<String>) propertyChangeEvent.getNewValue();
        update.setText("An order is ready to be delivered:");
        notifications.setItems(orderItems);
    }

}
