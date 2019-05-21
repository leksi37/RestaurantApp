package client.startApplications;

import basicClasses.Views;
import basicClasses.clients;
import client.model.logIn.LogInModel;
import client.model.logIn.LogInModelImpl;
import client.networking.logIn.LogInSocket;
import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartLogIn extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LogInModel model= new LogInModelImpl();
        System.out.println("Start");
        //String.valueOf(clients.CUSTOMER_CLIENT);

        LogInSocket socket = new LogInSocket(model);
        model.addSocket(socket);
        ViewHandler viewHandler= new ViewHandler(primaryStage, model);
        viewHandler.openView(Views.ON_OPEN);
    }
}
