package client.model.logIn;

import BasicClasses.clients;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelImpl;

public class LogInModelImpl implements LogInModel {

    CustomerModel customerModel;

    @Override
    public void connectSocket(clients type) {

    }

    @Override
    public CustomerModel getCustomerModel() {
        if(customerModel==null)
             customerModel= new CustomerModelImpl();


        return customerModel;
    }
}
