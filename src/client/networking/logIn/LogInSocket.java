package client.networking.logIn;

import basicClasses.ClientType;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.model.logIn.LogInModel;
import client.model.waiter.WaiterModel;
import client.networking.chef.ChefSocketClient;
import client.networking.customer.CustomerSocketClient;
import client.networking.waiter.WaiterSocketClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LogInSocket {

    private Socket socket;
    private LogInModel model;

    private ObjectOutputStream outToServer;

    public LogInSocket(LogInModel model) {
        try {
            this.model = model;
            this.socket = new Socket("localhost", 2910);
            outToServer= new ObjectOutputStream(socket.getOutputStream());
        }catch(IOException e){e.printStackTrace();}
    }

    public void connectCustomer(){
        try {
            outToServer.writeObject(ClientType.CUSTOMER_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startCustomer();
    }

    public void startCustomer(){
        CustomerModel customerModel= model.getCustomerModel();
        CustomerSocketClient customerSocketClient= new CustomerSocketClient(customerModel, socket);
        customerModel.addClient(customerSocketClient);
    }

    public void connectChef(){
        try {
            outToServer.writeObject(ClientType.CHEF_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startChef();
    }

    public void startChef(){
        ChefModel chefModel = model.getChefModel();
        ChefSocketClient chefSocketChefClient = new ChefSocketClient(chefModel, socket);

        chefModel.addClient(chefSocketChefClient);
    }

    public void connectWaiter(){
        try {
            outToServer.writeObject(ClientType.WAITER_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startWaiter();
    }

    public void startWaiter(){
        WaiterModel waiterModel = model.getWaiterModel();
        WaiterSocketClient waiterSocketClient= new WaiterSocketClient(waiterModel, socket);
        waiterModel.addClient(waiterSocketClient);
    }
}
