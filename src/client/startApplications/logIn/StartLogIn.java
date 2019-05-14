package client.startApplications.logIn;

import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.model.customer.CustomerModelImpl;
import client.model.logIn.LogInModel;
import client.model.logIn.LogInModelImpl;
import client.networking.logIn.LogInClient;
import client.networking.logIn.SocketLogIn;
import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartLogIn extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LogInModel model= new LogInModelImpl();
        LogInClient client= new SocketLogIn(model);
        ViewHandler viewHandler = new ViewHandler(primaryStage,model);
        viewHandler.openView(Views.LOGIN);
    }
}
