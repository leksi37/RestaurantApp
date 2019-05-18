package client.viewModel.logIn;

import basicClasses.Views;
import client.model.logIn.LogInModel;
import client.view.ViewHandler;
import client.viewModel.ViewModels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class OnOpenViewModel implements ViewModels {

    private ViewHandler viewHandler;
    private PropertyChangeSupport support= new PropertyChangeSupport(this); //maybe not needed
    private LogInModel model;

    public OnOpenViewModel(LogInModel model, ViewHandler viewHandler ){
        this.model=model;
        this.viewHandler=viewHandler;
    }

    public void addListener(String name, PropertyChangeListener listener){
        if(name== null)
            support.addPropertyChangeListener(listener);
        else support.addPropertyChangeListener(name, listener);
    }

    public void openCustomer(){
        viewHandler.openView(Views.MENU_FRONT);
        model.connectCustomer();
    }

    public void openWaiter(){
        viewHandler.openView(Views.MENU_FRONT); //for now
        model.connectWaiter();
    }

    public void openChef(){
        viewHandler.openView(Views.CHEF_FRONT);
        model.connectChef();
    }
}
