package client.viewModel.customer;

import BasicClasses.MenuItem;
import BasicClasses.Views;
import BasicClasses.type;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;

import java.util.ArrayList;

public class CategoryListViewModel implements ViewModels {

    public ViewHandler viewHandler;
    public CustomerModel model;
    private ArrayList<MenuItem> menuItems;


    public CategoryListViewModel(CustomerModel model, ViewHandler viewHandler) {
        this.model=model;
        this.viewHandler = viewHandler;
        menuItems = new ArrayList();

    }

    public void openCategoryListItems(String category) {

        viewHandler.setCategory(type.valueOf(category));
        viewHandler.openView(Views.ITEMS);
    }


}
