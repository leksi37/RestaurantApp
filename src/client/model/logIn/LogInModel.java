package client.model.logIn;

import BasicClasses.clients;
import client.model.customer.CustomerModel;

public interface LogInModel {
    void connectSocket(clients type);
    CustomerModel getCustomerModel();
}

