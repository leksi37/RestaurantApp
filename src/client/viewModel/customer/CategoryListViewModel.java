package client.viewModel.customer;

import BasicClasses.MenuItem;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.MenuProxy;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CategoryListViewModel {

    public ViewHandler viewHandler;
    public CustomerModel model;
    private ArrayList<MenuItem> menuItems;


    public CategoryListViewModel(CustomerModel model, ViewHandler viewHandler) {
        this.model=model;
        this.viewHandler = viewHandler;
        menuItems = new ArrayList();

    }

    public void openCategoryListItems(String category) {

        viewHandler.openCategoryListItems(category);

    }


}
