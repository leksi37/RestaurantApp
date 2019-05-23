package client.viewModel.Chef;

import basicClasses.Order;
import client.model.chef.ChefModel;
import client.viewModel.ViewModels;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChefViewModel implements ViewModels {
    private ChefModel model;
//    private PropertyChangeSupport changeSupport;
    private ObservableList<String> orders = FXCollections.observableArrayList();
    private PropertyChangeSupport changeSupport;

    public ChefViewModel(ChefModel model){
        this.model = model;
//        changeSupport = new PropertyChangeSupport(this);
        this.model.addListeners("OrderForChefAdded", this :: getOrderUpdate);
        this.model.addListeners("gotOrders", this :: gotOrders);
        this.model.addListeners("gotOrder", this :: gotOrder);
        changeSupport = new PropertyChangeSupport(this);
    }

    public void addListener(String name, PropertyChangeListener listener) {
        if (name == null)
            changeSupport.addPropertyChangeListener(listener);
        else changeSupport.addPropertyChangeListener(name, listener);
    }

    private void gotOrder(PropertyChangeEvent propertyChangeEvent) {
        Order o = (Order)propertyChangeEvent.getNewValue();
        int i = orders.indexOf(o.getTableId());
        Platform.runLater(() -> {
            orders.remove(o.getTableId());
            orders.add(i, o.getTableId());
        });
        changeSupport.firePropertyChange("refresh", null, null);
    }

    private void gotOrders(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() -> {
            setOrders((ArrayList<Order>) propertyChangeEvent.getNewValue());
        });
    }

    private void setOrders(ArrayList<Order> list)
    {
        for(int i = 0; i < list.size(); ++i)
        {
            orders.add(list.get(i).getTableId());
        }
    }

    public void getOrderUpdate(PropertyChangeEvent changeEvent){
        ArrayList<Order> newOrders = (ArrayList) changeEvent.getNewValue();
        for(int i = orders.size(); i < newOrders.size(); ++i)
        {
            orders.add(newOrders.get(i).getTableId());
        }
    }

//    public void addListener(String name, PropertyChangeListener listener)
//    {
//        if(name == null || name.equals(""))
//            changeSupport.addPropertyChangeListener(listener);
//        else
//            changeSupport.addPropertyChangeListener(name, listener);
//    }

    public ObservableList getOrders() {
        return orders;
    }

    public void fetchOrders() {
        System.out.println("chef view model");
        model.fetchOrders();
    }

    public Order getOrder(int selectedIndex) {
        return model.getOrder(selectedIndex);
    }
}
