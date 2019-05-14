package client.viewModel.customer;

import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;
import javafx.application.Platform;


public class MenuFrontViewModel implements ViewModels {

    private CustomerModel model;

    public MenuFrontViewModel(CustomerModel model)
    {
        this.model = model;
    }

    public void openCategoryList(ViewHandler viewHandler) {
        Platform.runLater(() -> viewHandler.openView(Views.CATEGORIES));
    }
}
