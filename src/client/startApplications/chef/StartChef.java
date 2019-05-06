package client.startApplications.chef;

import BasicClasses.Views;
import client.model.chef.ChefModel;
import client.model.chef.ChefModelImpl;
import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartChef extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            ChefModel model= new ChefModelImpl();
  //          Client client= new SocketClient(model);
//            model.addClient(client);
            ViewHandler viewHandler = new ViewHandler(primaryStage, model);
            viewHandler.openView(Views.CHEF_FRONT);
    }
}
