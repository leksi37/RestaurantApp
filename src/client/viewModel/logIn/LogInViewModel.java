package client.viewModel.logIn;

import basicClasses.ClientType;
import basicClasses.Views;
import client.model.chef.ChefModel;
import client.model.logIn.LogInModel;
import client.model.logIn.modelFactory.ClientModel;
import client.model.waiter.WaiterModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class LogInViewModel implements ViewModels {
    private StringProperty password;
    private ViewHandler viewHandler;
    private LogInModel mainModel;
    private ClientModel model;
    private ClientType type;
    private StringProperty errorText;


    public LogInViewModel(ViewHandler vh, LogInModel m) {
        password = new SimpleStringProperty();
        viewHandler = vh;
        mainModel = m;
        errorText = new SimpleStringProperty("");
    }

    public void setModel(ClientType type){
        this.type=type;

        model.addListeners("passwordDisapproved", this::wrongPassword);
        model.addListeners("passwordApproved", this::goodPassword);
    }

    private void goodPassword(PropertyChangeEvent propertyChangeEvent) {
        password.setValue("");
        if(type.equals(ClientType.WAITER_CLIENT))
            viewHandler.openView(Views.WAITER);
        else if(type.equals(ClientType.CHEF_CLIENT))
            viewHandler.openView(Views.CHEF);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void logIn() {
        mainModel.checkLogIn(password.getValue());
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
