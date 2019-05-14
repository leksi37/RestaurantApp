package client.view.logIn;

import client.viewModel.logIn.LogInViewModel;
import javafx.event.ActionEvent;

public class LogIn {

    private LogInViewModel viewModel;

    public void init(LogInViewModel viewModel){
        this.viewModel=viewModel;
    }

    public void onCustomerButton(ActionEvent actionEvent) {
        viewModel.openCustomerView();
    }

    public void onChefButton(ActionEvent actionEvent) {
        viewModel.openChefView();
    }

    public void onWaiterButton(ActionEvent actionEvent) {
        viewModel.openWaiterView();
    }

    public void onAdminButton(ActionEvent actionEvent) {
        viewModel.openAdministratorView();
    }
}
