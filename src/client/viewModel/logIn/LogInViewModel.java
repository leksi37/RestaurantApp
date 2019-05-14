package client.viewModel.logIn;

import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelImpl;
import client.model.logIn.LogInModel;
import client.networking.customer.Client;
import client.networking.customer.SocketClient;
import client.view.ViewHandler;
import client.viewModel.ViewModels;

public class LogInViewModel implements ViewModels {

    private ViewHandler viewHandler;
    private LogInModel model;

    public LogInViewModel(LogInModel model, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.model = model;
    }

    public void openCustomerView(){
        CustomerModel customerModel= new CustomerModelImpl();
        Client client= new SocketClient(customerModel);
        customerModel.addClient(client);
        viewHandler.openView(Views.MENU_FRONT);
    }

    public void openChefView(){
        viewHandler.openView(Views.CHEF);
    }

    public void openWaiterView(){
        viewHandler.openView(Views.WAITER);
    }

    public void openAdministratorView(){
        viewHandler.openView(Views.ADMIN);
    }
}
