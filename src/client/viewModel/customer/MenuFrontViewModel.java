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
    private ViewHandler viewHandler;

    public MenuFrontViewModel(CustomerModel model, ViewHandler vh)
    {
        this.model = model;
        this.viewHandler = vh;
        this.model.addListeners("orderClosed", this :: orderClosed);
    }

    private void orderClosed(PropertyChangeEvent propertyChangeEvent) {
        viewHandler.openView(Views.MENU_FRONT);
    }

    public void openCategoryList() {
        Platform.runLater(() -> viewHandler.openView(Views.CATEGORIES));
    }

    public void requestWaiter() {
        model.requestWaiter();
    }

    public void requestReceipt() {
        model.requestReceipt();
    }
}
