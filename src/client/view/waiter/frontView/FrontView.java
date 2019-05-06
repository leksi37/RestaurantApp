package client.view.waiter.frontView;

import client.view.ViewHandler;
import client.viewModel.Waiter.FrontViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javax.swing.text.View;

public class FrontView {
    private FrontViewModel viewModel;
    private ObservableList<String> orderItems = FXCollections.observableArrayList();
    private ViewHandler viewHandler;

    public FrontView(ViewHandler viewHandler){
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
       // viewModel.addListener("Done order", this::getOrderDetails);
    }

    public void getOrderDetails(){
       // orderItems = (ObservableList<String>) propertyChangeEvent.getNewValue();
        update.setText("An order is ready to be delivered:");
        notifications.setItems(orderItems);
    }

}
