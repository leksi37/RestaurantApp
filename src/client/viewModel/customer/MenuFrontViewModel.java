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
    private StringProperty label;
    private CustomerModel model;
    private PropertyChangeSupport support;

    public MenuFrontViewModel(CustomerModel model)
    {
        label = new SimpleStringProperty();
        this.model = model;
        support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener("Waiter requested", this::notifiedWaiter);
    }

    private void notifiedWaiter(PropertyChangeEvent changeEvent) {
        label.setValue(changeEvent.getNewValue().toString());
    }


    public void openCategoryList(ViewHandler viewHandler) {
        Platform.runLater(() -> viewHandler.openView(Views.CATEGORIES));
    }

    public void requestWaiter() {
        Platform.runLater(() -> model.requestWaiter());
    }

    public Property<String> getLabel() {
        return label;
    }
}
