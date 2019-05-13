package client.view.OnOpen;

import BasicClasses.Views;
import client.view.ViewHandler;
import javafx.event.ActionEvent;

import javax.swing.text.View;

public class OnOpen {
    private ViewHandler viewHandler;

    public void openCustomer(ActionEvent actionEvent) {
        viewHandler.openView(Views.MENU_FRONT);
    }

    public void openManager(ActionEvent actionEvent) {

    }

    public void openChef(ActionEvent actionEvent) {
        viewHandler.openView(Views.CHEF_FRONT);
    }

    public void openWaiter(ActionEvent actionEvent) {

    }

    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
}
