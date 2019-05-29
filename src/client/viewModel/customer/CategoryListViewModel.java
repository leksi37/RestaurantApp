package client.viewModel.customer;

import basicClasses.CategoryType;
import basicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;

public class CategoryListViewModel implements ViewModels {

    public CustomerModel model;
    private ViewHandler viewHandler;

    public CategoryListViewModel(ViewHandler viewHandler, CustomerModel model) {
        this.model = model;
        this.viewHandler = viewHandler;

    }

    public void openCategory(CategoryType category) {
        System.out.println("viewModel");
        viewHandler.setCategory(category);
        viewHandler.openView(Views.ITEMS);
    }


}
