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
    private MenuFrontViewModel menuFrontViewModel;

    @FXML
    private Label orderStatus;

    @FXML
    public void onClick(){
        menuFrontViewModel.openCategoryList(viewHandler);
    }

    public void setOrderStatus(String status){
        orderStatus.setText(status);
    }


    public void init(MenuFrontViewModel vm, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        menuFrontViewModel = vm;

    }
}
