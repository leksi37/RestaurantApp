package client.view.customer.menuFront;


import client.view.ViewHandler;
import client.viewModel.customer.MenuFrontViewModel;
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

    @FXML
    public void requestWaiter(){
        menuFrontViewModel.requestWaiter();
        waiterButton.setDisable(true);
    }

    public void setOrderStatus(String status){
        orderStatus.setText(status);
    }


    public void init(MenuFrontViewModel vm, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        menuFrontViewModel = vm;
        //orderStatus.textProperty().bind(menuFrontViewModel.getLabel());
    }
}
