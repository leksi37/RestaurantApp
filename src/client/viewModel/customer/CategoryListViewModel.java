package client.viewModel.customer;

import BasicClasses.MenuItem;
import BasicClasses.Views;
import BasicClasses.type;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;

import java.util.ArrayList;

public class CategoryListViewModel implements ViewModels {

    public CustomerModel model;
    private ArrayList<MenuItem> menuItems;


    public CategoryListViewModel(CustomerModel model) {
        this.model=model;
        menuItems = new ArrayList();

    }

//    public void openCategoryListItems(type category) {
//        System.out.println("viewModel");
//       viewHandler.setCategory(category);
//       viewHandler.openView(Views.ITEMS);
//    }


}
