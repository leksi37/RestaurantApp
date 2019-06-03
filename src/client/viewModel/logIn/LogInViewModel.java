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
    private StringProperty hello;
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
        if(type.equals(ClientType.WAITER_CLIENT))
        {
            model=mainModel.getWaiterModel();
            hello = new SimpleStringProperty("Hello, dear waiter!");
        }
        else if(type.equals(ClientType.CHEF_CLIENT))
        {
            model=mainModel.getChefModel();
            hello = new SimpleStringProperty("Hello, master chef!");
        }

        this.type=type;

        model.addListeners("passwordDisapproved", this::wrongPassword);
        model.addListeners("passwordApproved", this::goodPassword);
        model.addListeners("chefPasswordApproved", this::chefGoodPassword);
    }

    private void chefGoodPassword(PropertyChangeEvent propertyChangeEvent) {
        password.setValue("");
        Platform.runLater(() ->
                errorText.setValue("")
                );

        if(type.equals(ClientType.CHEF_CLIENT))
            viewHandler.openView(Views.CHEF);
    }

    private void goodPassword(PropertyChangeEvent propertyChangeEvent) {
        password.setValue("");
        Platform.runLater(() ->
                errorText.setValue("")
        );

        if(type.equals(ClientType.WAITER_CLIENT))
            viewHandler.openView(Views.WAITER);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void logIn() {

        if(type.equals(ClientType.WAITER_CLIENT)){
            ((WaiterModel)model).checkLogIn(password.getValue());}
        else if(type.equals(ClientType.CHEF_CLIENT))
            ((ChefModel)model).checkLogIn(password.getValue());
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

    public StringProperty helloProperty() {
        return hello;
    }
}
