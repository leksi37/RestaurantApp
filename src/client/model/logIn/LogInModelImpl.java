package client.model.logIn;

import basicClasses.clients;
import client.model.chef.ChefModel;
import client.model.chef.ChefModelImpl;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelImpl;
import client.model.waiter.WaiterModel;
import client.model.waiter.WaiterModelImpl;
import client.networking.logIn.LogInSocket;

public class LogInModelImpl implements LogInModel {

    private CustomerModel customerModel;
    private ChefModel chefModel;
    private WaiterModel waiterModel;

    private LogInSocket socket;


    @Override
    public void addSocket(LogInSocket socket) {
        this.socket=socket;
    }

    @Override
    public ChefModel getChefModel() {
        if(chefModel==null)
            chefModel= new ChefModelImpl();
        return chefModel;
    }

    @Override
    public WaiterModel getWaiterModel() {
        if(waiterModel==null)
            waiterModel= new WaiterModelImpl();
        return waiterModel;
    }

    @Override
    public CustomerModel getCustomerModel() {
        if(customerModel==null)
             customerModel= new CustomerModelImpl();


        return customerModel;
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
