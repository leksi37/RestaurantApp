package client.startApplications.customer;

import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelImpl;
import client.networking.Client;
import client.networking.SocketClient;
import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartCustomer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomerModel model= new CustomerModelImpl();
        Client client= new SocketClient(model);
        model.addClient(client);
        ViewHandler viewHandler = new ViewHandler(primaryStage, model);
        viewHandler.openMenuFront();
    }
}
