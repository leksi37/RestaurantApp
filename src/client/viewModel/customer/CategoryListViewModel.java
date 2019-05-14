package client.viewModel.customer;

import BasicClasses.MenuItem;
import BasicClasses.Views;
import BasicClasses.type;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;

import javax.swing.text.View;
import java.util.ArrayList;

public class CategoryListViewModel implements ViewModels {

    public CustomerModel model;
    private ViewHandler viewHandler;

    public CategoryListViewModel(ViewHandler viewHandler, CustomerModel model) {
        this.model = model;
        this.viewHandler = viewHandler;

    }

    public void openCategory(type category) {
        System.out.println("viewModel");
        viewHandler.setCategory(category);
        viewHandler.openView(Views.ITEMS);
    }


}
