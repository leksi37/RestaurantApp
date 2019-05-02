package client.viewModel.customer;

import client.model.customer.CustomerModel;
import client.view.ViewHandler;


public class MenuFrontViewModel {

    private CustomerModel model;
    private ViewHandler viewHandler;

    public MenuFrontViewModel(CustomerModel model, ViewHandler viewHandler)
    {
        this.model = model;
        this.viewHandler = viewHandler;
    }

    public void openCategoryList() {
       viewHandler.openCategoryList();
       //ConnectionPool?
       //model.addClient();
       // model.addOrderToServer(new Order("some text"));
    }
}
