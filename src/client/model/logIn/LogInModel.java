package client.model.logIn;

import BasicClasses.SocketClientType;
import client.model.customer.CustomerModel;

public interface LogInModel {
    void connectSocket(SocketClientType type);
    CustomerModel getCustomerModel();
}

