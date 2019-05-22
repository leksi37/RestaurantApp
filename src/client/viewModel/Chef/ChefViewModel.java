package client.viewModel.Chef;

import basicClasses.Order;
import client.model.chef.ChefModel;
import client.viewModel.ViewModels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChefViewModel implements ViewModels {
    ChefModel model;
    private PropertyChangeSupport changeSupport;
    ArrayList<Order> orders = new ArrayList<>();

    public ChefViewModel(ChefModel model){
        this.model = model;
        changeSupport = new PropertyChangeSupport(this);
        this.model.addListeners("OrderForChefAdded", this :: getOrderUpdate);
    }

    public void getOrderUpdate(PropertyChangeEvent changeEvent){
        orders = (ArrayList) changeEvent.getNewValue();
        changeSupport.firePropertyChange("NewOrderChef", null, orders);
    }

    public void addListener(String name, PropertyChangeListener listener)
    {
        if(name == null || name.equals(""))
            changeSupport.addPropertyChangeListener(listener);
        else
            changeSupport.addPropertyChangeListener(name, listener);
    }
}
