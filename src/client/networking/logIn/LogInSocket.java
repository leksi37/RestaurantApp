package client.networking.logIn;

import basicClasses.ClientType;
import basicClasses.RequestType;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.model.logIn.LogInModel;
import client.model.waiter.WaiterModel;
import client.networking.chef.ChefSocketClient;
import client.networking.customer.CustomerSocketClient;
import client.networking.waiter.WaiterSocketClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LogInSocket implements LogIn {

    private Socket socket;
    private LogInModel model;

    private LogInSocketHandler handler;
    private ClientType type;

    private ObjectOutputStream outToServer;

    public LogInSocket(LogInModel model) {
        try {
            this.model = model;
            this.socket = new Socket("localhost", 2910);
            handler= new LogInSocketHandler(this,new ObjectOutputStream(socket.getOutputStream()),new ObjectInputStream(socket.getInputStream()) );
            outToServer= new ObjectOutputStream(socket.getOutputStream());
        }catch(IOException e){e.printStackTrace();}
        Thread t= new Thread(handler);
        t.start();
    }

    private void startCustomer(){
        CustomerModel customerModel= model.getCustomerModel();
        CustomerSocketClient customerSocketClient= new CustomerSocketClient(customerModel, socket);
        customerModel.addClient(customerSocketClient);
    }


    private void startChef(){
        ChefModel chefModel = model.getChefModel();
        ChefSocketClient chefSocketChefClient = new ChefSocketClient(chefModel, socket);

        chefModel.addClient(chefSocketChefClient);
    }


    private void startWaiter(){
        WaiterModel waiterModel = model.getWaiterModel();
        WaiterSocketClient waiterSocketClient= new WaiterSocketClient(waiterModel, socket);
        waiterModel.addClient(waiterSocketClient);
    }

    @Override
    public void setClientType(ClientType type) {
        this.type=type;
        if(type.equals(ClientType.CUSTOMER_CLIENT))
            startCustomer();
    }

    @Override
    public ClientType getClientType() {
        return type;
    }

    @Override
    public void connectClient(){
        handler.connectClient(type);
    }

    @Override
    public void checkPassword(String value) {
        handler.checkPassword(value);
    }

    @Override
    public void passwordCheckResult(RequestType type) {
        if(type.equals(RequestType.CHEF_APPROVED))
            startChef();
        else if(type.equals(RequestType.WAITER_APPROVED))
            startWaiter();
    }


}
