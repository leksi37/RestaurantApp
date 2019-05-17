package client.view.OnOpen;

import basicClasses.Views;
import client.view.ViewHandler;
import client.viewModel.logIn.OnOpenViewModel;
import javafx.event.ActionEvent;

import javax.swing.text.View;

public class OnOpen {

    private OnOpenViewModel viewModel;

    public void init(OnOpenViewModel viewModel) {
       this.viewModel=viewModel;
    }

    public void openCustomer(ActionEvent actionEvent) {
        viewModel.openCustomer();
    }

    public void openChef(ActionEvent actionEvent) {
        viewModel.openChef();
    }

    public void openWaiter(ActionEvent actionEvent) {
        viewModel.openWaiter();
    }

    public void openManager(ActionEvent actionEvent) {
    }
}
