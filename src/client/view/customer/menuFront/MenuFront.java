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
    private MenuFrontViewModel menuFrontViewModel;

    @FXML
    private Label orderStatus;

    @FXML
    private Button waiterButton;

    @FXML Button receiptButton;

    @FXML
    public void onClick(){
        menuFrontViewModel.openCategoryList();
    }

    public void orderPrepared(){
        orderStatus.setText("Your order is on the way");
    }

    public void newOrder(){
        orderStatus.setText("");
    }

    public void enableRequestReceipt(){
        receiptButton.setDisable(false);
    }

    public void requestReceipt(){
        menuFrontViewModel.requestReceipt();
        receiptButton.setDisable(true);
    }

    @FXML
    public void requestWaiter(){
        menuFrontViewModel.requestWaiter();
        waiterButton.setDisable(true);
    }

    public void init(MenuFrontViewModel vm) {
        menuFrontViewModel = vm;

    }

    public void clearLabel() {
        orderStatus.setText("");
    }
}
