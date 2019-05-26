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
            this.socket = new Socket("localHost", 2910);
            outToServer= new ObjectOutputStream(socket.getOutputStream());
        }catch(IOException e){e.printStackTrace();}
    }

    public void connectCustomer(){
        try {
            outToServer.writeObject(ClientType.CUSTOMER_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Trying to connect customer");
        startCustomer();
    }

    public void startCustomer(){
        CustomerModel customerModel= model.getCustomerModel();
        System.out.println("customer model, socket: "+customerModel);
        CustomerSocketClient customerSocketClient= new CustomerSocketClient(customerModel, socket);
        customerModel.addClient(customerSocketClient);
        System.out.println("Connected customer customer");

    }

    public void connectChef(){
        try {
            outToServer.writeObject(ClientType.CHEF_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Trying to connect first");
        startChef();
    }

    public void startChef(){
        ChefModel chefModel = model.getChefModel();
        System.out.println("got the chef model");
        ChefSocketClient chefSocketClient = new ChefSocketClient(chefModel, socket);

        chefModel.addClient(chefSocketClient);

    }

    public void connectWaiter(){
        try {
            outToServer.writeObject(ClientType.WAITER_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Trying to connect the waiter");
        startWaiter();
    }

    public void startWaiter(){
        WaiterModel waiterModel = model.getWaiterModel();
        System.out.println("Water model, socket: " + waiterModel);
        WaiterSocketClient waiterSocketClient= new WaiterSocketClient(waiterModel, socket);
        waiterModel.addClient(waiterSocketClient);
        System.out.println("Connected waiter client");

    }
}
