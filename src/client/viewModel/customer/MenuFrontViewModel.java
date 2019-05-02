package client.viewModel.customer;

import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;


public class MenuFrontViewModel implements ViewModels {

    private CustomerModel model;
    private ViewHandler viewHandler;

    public MenuFrontViewModel(CustomerModel model, ViewHandler viewHandler)
    {
        this.model = model;
        this.viewHandler = viewHandler;
    }

    public void openCategoryList() {
       viewHandler.openView(Views.CATEGORIES);
       //ConnectionPool?
       //model.addClient();
       // model.addOrderToServer(new Order("some text"));
    }
}
