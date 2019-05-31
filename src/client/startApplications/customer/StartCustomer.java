package client.startApplications.customer;

import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartCustomer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomerModel model= new CustomerModelImpl();
        System.out.println("Start");
    /*CustomerClient customerClient = new CustomerSocketClient(model);
        model.addClient(customerClient);
        ViewHandler viewHandler = new ViewHandler(primaryStage, model);
        viewHandler.openView(Views.MENU_FRONT);*/
    }
}