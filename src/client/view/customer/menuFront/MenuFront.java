package client.view.customer.menuFront;

import basicClasses.Views;
import client.view.ViewHandler;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.MenuFrontViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuFront {
    private ViewHandler viewHandler;
    private MenuFrontViewModel menuFrontViewModel;

    @FXML
    private Label orderStatus;

    @FXML
    private Button waiterButton;

    @FXML
    public void onClick(){
        menuFrontViewModel.openCategoryList(viewHandler);
    }

    public void orderPrepared(){
        orderStatus.setText("Your order is on the way");
    }

    public void newOrder(){
        orderStatus.setText("");
    }

    @FXML
    public void requestWaiter(){
        menuFrontViewModel.requestWaiter();
        waiterButton.setDisable(true);
    }

    public void init(MenuFrontViewModel vm, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        menuFrontViewModel = vm;

    }
}
