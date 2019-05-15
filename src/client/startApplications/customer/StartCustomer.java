package client.startApplications.customer;

import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelImpl;
import client.networking.customer.CustomerClient;
import client.networking.customer.CustomerSocketCustomerClient;
import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartCustomer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomerModel model= new CustomerModelImpl();
        CustomerClient customerClient = new CustomerSocketCustomerClient(model);
        model.addClient(customerClient);
        ViewHandler viewHandler = new ViewHandler(primaryStage, model);
        viewHandler.openView(Views.ON_OPEN);
    }
}