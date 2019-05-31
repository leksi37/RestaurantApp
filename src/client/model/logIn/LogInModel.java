package client.model.logIn;

import basicClasses.ClientType;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.model.waiter.WaiterModel;
import client.networking.logIn.LogIn;

import java.beans.PropertyChangeListener;

public interface LogInModel {
    void addSocket(LogIn socket);
    void addListeners(String name, PropertyChangeListener listener);

    ChefModel getChefModel();
    WaiterModel getWaiterModel();
    CustomerModel getCustomerModel();

    void connectCustomer();
    void connectWaiter();
    void connectChef();

    void checkLogIn(String value);
    void passwordDisapproved();
    void passwordApproved();
}

