package client.model.logIn;

import basicClasses.ClientType;
import client.model.chef.ChefModel;
import client.model.chef.ChefModelImpl;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelImpl;

import client.model.logIn.modelFactory.ClientModelFactory;
import client.model.waiter.WaiterModel;
import client.model.waiter.WaiterModelImpl;
import client.networking.logIn.LogInSocket;

public class LogInModelImpl implements LogInModel {

    private LogInSocket socket;

    @Override
    public void addSocket(LogInSocket socket) {
        this.socket=socket;
    }

    @Override
    public ChefModel getChefModel() {
        return (ChefModelImpl) ClientModelFactory.getModel(ClientType.CHEF_CLIENT);
    }

    @Override
    public WaiterModel getWaiterModel() {
        return (WaiterModelImpl) ClientModelFactory.getModel(ClientType.WAITER_CLIENT);
    }

    @Override
    public CustomerModel getCustomerModel() {
        CustomerModel model= (CustomerModelImpl) ClientModelFactory.getModel(ClientType.CUSTOMER_CLIENT);
        return model;
    }


    @Override
    public void connectCustomer() {
        socket.connectCustomer();
    }

    @Override
    public void connectWaiter() {
        socket.connectWaiter();
    }

    @Override
    public void connectChef() {
        socket.connectChef();
    }
}
