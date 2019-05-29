package client.viewModel.chef;

import basicClasses.Views;
import client.model.chef.ChefModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class ChefLogInViewModel implements ViewModels {
    private StringProperty password;
    private ViewHandler viewHandler;
    private ChefModel model;
    private StringProperty errorText;


    public ChefLogInViewModel(ViewHandler vh, ChefModel m) {
        password = new SimpleStringProperty();
        viewHandler = vh;
        model = m;
        errorText = new SimpleStringProperty("");
        model.addListeners("chefPasswordDisapproved", this::wrongPassword);
        model.addListeners("chefPasswordApproved", this::goodPassword);
    }

    private void goodPassword(PropertyChangeEvent propertyChangeEvent) {
        viewHandler.openView(Views.CHEF);
        password.setValue("");
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void logIn() {
        System.out.println("log in view model");
        model.checkLogIn(password.getValue());
    }

    public void wrongPassword(PropertyChangeEvent evt)
    {
        Platform.runLater(() ->{
            errorText.setValue("Wrong password");
            password.setValue("");
                });
    }

    public StringProperty errorTextProperty() {
        return errorText;
    }
}
