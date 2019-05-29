package client.viewModel.customer;

import basicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;


public class MenuFrontViewModel implements ViewModels {
    private CustomerModel model;

    public MenuFrontViewModel(CustomerModel model)
    {
        this.model = model;
    }

    public void openCategoryList(ViewHandler viewHandler) {
        Platform.runLater(() -> viewHandler.openView(Views.CATEGORIES));
    }

    public void requestWaiter() {
        model.requestWaiter();
    }

    public void requestReceipt() {
        model.requestReceipt();
    }
}
