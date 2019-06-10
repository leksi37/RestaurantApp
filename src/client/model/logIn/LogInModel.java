package client.model.logIn;

import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.model.waiter.WaiterModel;
import client.networking.logIn.LogInSocket;

public interface LogInModel {
    void addSocket(LogInSocket socket);

    ChefModel getChefModel();
    WaiterModel getWaiterModel();
    CustomerModel getCustomerModel();

    void connectCustomer();
    void connectWaiter();
    void connectChef();
}

