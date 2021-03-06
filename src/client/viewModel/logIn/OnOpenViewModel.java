package client.viewModel.logIn;

import basicClasses.Views;
import client.model.logIn.LogInModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OnOpenViewModel implements ViewModels {

    private ViewHandler viewHandler;
    private LogInModel model;

    public OnOpenViewModel(LogInModel model, ViewHandler viewHandler ){
        this.model=model;
        this.viewHandler=viewHandler;
    }

    public void openCustomer(){
        viewHandler.openView(Views.MENU_FRONT);
        model.connectCustomer();
    }

    public void openWaiter(){
        viewHandler.openView(Views.WAITER_LOG_IN);
        model.connectWaiter();
    }

    public void openChef(){
        viewHandler.openView(Views.CHEF_LOG_IN);
        model.connectChef();
    }
}
