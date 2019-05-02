package client.viewModel.customer;

import BasicClasses.MenuItem;
import BasicClasses.Order;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class CategoryListItemsViewModel {
    //IT'S ABOUT STORING DATA, IT'S NOT SUPPOSED TO BE IN THE VIEW MODEL, BUT IN THE MODEL
//    private Order currentOrder;

    private ViewHandler viewHandler;
    private CustomerModel model;
    private ArrayList<MenuItem> items;
    private PropertyChangeSupport changeSupport;

    public CategoryListItemsViewModel(CustomerModel model, ViewHandler viewHandler){
        this.viewHandler = viewHandler;
//        currentOrder = null;
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

//    public Order getOrder(){
//        return currentOrder;
//    }

//    public void openOrderItemListView(Order order){
//        if (currentOrder != null)
//            viewHandler.openOrderItemsList(order);
//    }

    public void getItems(String type) {
        model.requestMenuCategory(type);
    }

    public void removeFromOrder(String id, int i) {
        model.removeItem(id, i);
    }

    public void back() {
        viewHandler.openCategoryList();
    }

    public void seeOrder() {
        viewHandler.openOrderItemsList(model.getOrder());
    }
}
