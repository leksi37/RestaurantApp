package client.viewModel.customer;

import BasicClasses.MenuItem;
import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CategoryListItemsViewModel implements ViewModels {

    private CustomerModel model;
    private ArrayList<MenuItem> items;
    private PropertyChangeSupport changeSupport;

    public CategoryListItemsViewModel(CustomerModel model){


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

}
