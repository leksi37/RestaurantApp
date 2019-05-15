package client.startApplications.chef;

import BasicClasses.Views;
import client.model.chef.ChefModel;
import client.model.chef.ChefModelImpl;
import client.networking.chef.ChefClient;
import client.networking.chef.ChefSocketChefClient;
import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartChef extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            ChefModel model= new ChefModelImpl();
            ChefClient chefClient = new ChefSocketChefClient(model);
            model.addClient(chefClient);
            ViewHandler viewHandler = new ViewHandler(primaryStage, model);
            viewHandler.openView(Views.ON_OPEN);
    }
}
