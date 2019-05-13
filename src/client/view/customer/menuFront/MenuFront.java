package client.view.customer.menuFront;

import BasicClasses.Views;
import client.view.ViewHandler;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.MenuFrontViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuFront {
    private ViewHandler viewHandler;


    @FXML
    private Label orderStatus;

    @FXML
    public void onClick(){
        Platform.runLater(() -> {
            viewHandler.openView(Views.CATEGORIES);
        });
    }

    public void setOrderStatus(String status){
        orderStatus.setText(status);
    }


    public void init( ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
}
