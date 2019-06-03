package client.viewModel.customer;

import basicClasses.CategoryType;
import basicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;

public class CategoryListViewModel implements ViewModels {

    public CustomerModel model;
    private ViewHandler viewHandler;

    public CategoryListViewModel(ViewHandler viewHandler, CustomerModel model) {
        this.model = model;
        this.viewHandler = viewHandler;

        this.model.addListeners("orderClosed", this :: orderClosed);
    }

    private void orderClosed(PropertyChangeEvent propertyChangeEvent) {
        viewHandler.openView(Views.MENU_FRONT);
    }

    public void openCategory(CategoryType category) {
        viewHandler.setCategory(category);
        viewHandler.openView(Views.ITEMS);
    }


    public void backToMenuFront() {
        Platform.runLater(() -> {
            viewHandler.openView(Views.MENU_FRONT);
        });
    }
}
