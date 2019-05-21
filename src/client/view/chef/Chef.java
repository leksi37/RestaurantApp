package client.view.chef;

import basicClasses.Order;
import client.view.ViewHandler;
import client.viewModel.Chef.ChefViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.beans.PropertyChangeEvent;

public class Chef {
    private ChefViewModel viewModel;
    private ObservableList<Order> orders = FXCollections.observableArrayList();

    @FXML
    private ListView orderList;

    @FXML
    private void sendPartial(){

    }

    @FXML
    private void closeOrder(){

    }


    public void setItems(PropertyChangeEvent support){
        orders = (ObservableList) support.getNewValue();
        orderList.setItems(orders);
    }

    public void init(ChefViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addListener("NewOrderChef", this::setItems);
    }

}
