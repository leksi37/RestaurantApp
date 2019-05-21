package client.networking.logIn;

import basicClasses.clients;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.model.logIn.LogInModel;
import client.networking.chef.ChefSocketChefClient;
import client.networking.customer.CustomerSocketClient;

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
            outToServer.writeObject(clients.CUSTOMER_CLIENT);
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
        System.out.println("Connected customer client");

    }


    //just for show     fix later

    public void connectChef(){
        try {
            outToServer.writeObject(clients.CHEF_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Trying to connect first");
        startChef();
    }

    public void startChef(){
        ChefModel chefModel = model.getChefModel();
        ChefSocketChefClient chefSocketChefClient = new ChefSocketChefClient(chefModel, socket);
        chefModel.addClient(chefSocketChefClient);

    }


    public void connectWaiter(){
        try {
            outToServer.writeObject(clients.CUSTOMER_CLIENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Trying to connect first");
        startCustomer();
    }

    public void startWaiter(){
        CustomerModel customerModel= model.getCustomerModel();
        CustomerSocketClient customerSocketClient= new CustomerSocketClient(customerModel, socket);
        customerModel.addClient(customerSocketClient);

    }
}
