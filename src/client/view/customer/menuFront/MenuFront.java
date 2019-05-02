package client.view.customer.menuFront;

import client.view.ViewHandler;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.MenuFrontViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuFront {
    private MenuFrontViewModel menuFrontViewModel;

    @FXML
    private Label orderStatus;

    @FXML
    public void onClick(){
        menuFrontViewModel.openCategoryList();
    }

    //Not finished
    public void setOrderStatus(String status){
        orderStatus.setText(status);
    }


    public void init(ViewModelProvider vmp) {
        menuFrontViewModel = vmp.getMenuFrontViewModel();
    }
}
