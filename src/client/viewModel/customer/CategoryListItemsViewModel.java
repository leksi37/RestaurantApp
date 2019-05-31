package client.viewModel.customer;

import basicClasses.MenuItem;
import basicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CategoryListItemsViewModel implements ViewModels {
    private ViewHandler viewHandler;
    private CustomerModel model;
    private ArrayList<MenuItem> items;
    private PropertyChangeSupport changeSupport;

    public CategoryListItemsViewModel(ViewHandler viewHandler, CustomerModel model){
        this.viewHandler = viewHandler;
        changeSupport = new PropertyChangeSupport(this);
        this.model = model;
        this.model.addListeners("MenuItems", this :: gotItems);
    }

    public void addListener(String name, PropertyChangeListener listener)
    {
        if(name == null || name.equals(""))
            changeSupport.addPropertyChangeListener(listener);
        else
            changeSupport.addPropertyChangeListener(name, listener);
    }

    private void gotItems(PropertyChangeEvent propertyChangeEvent) {
        items = (ArrayList<MenuItem>) propertyChangeEvent.getNewValue();
        if(items.size()>0)
        changeSupport.firePropertyChange("gotItems", null, items);
    }

    public void addToOrder(String id, int quantity) {
        model.addItem(id, quantity);
    }

    public void getItems(String type) {
        model.requestMenuCategory(type);
    }

    public void removeFromOrder(String id, int i) {
        model.removeItem(id, i);
    }

    public void openOrder() {
        Platform.runLater(() -> {
            viewHandler.openView(Views.ORDER);
        });
    }

    public void sendBack() {
        Platform.runLater(() -> {
            viewHandler.openView(Views.CATEGORIES);
        });
    }
}
