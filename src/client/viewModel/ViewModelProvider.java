package client.viewModel;

import basicClasses.Views;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.model.logIn.LogInModel;
import client.model.waiter.WaiterModel;
import client.networking.waiter.WaiterSocketClient;
import client.view.ViewHandler;
import client.viewModel.Chef.ChefLogInViewModel;
import client.viewModel.Chef.ChefViewModel;
import client.viewModel.Waiter.WaiterViewModel;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;
import client.viewModel.logIn.OnOpenViewModel;

import javax.swing.text.View;

public class ViewModelProvider {

    private ViewHandler viewHandler;

    //models
    private LogInModel mainModel;
    private CustomerModel customerModel;
    private WaiterModel waiterModel;
    private ChefModel chefModel;

    //viewModels
    private OnOpenViewModel onOpenViewModel;

    private MenuFrontViewModel menuFrontViewModel;
    private CategoryListViewModel categoryListViewModel;
    private CategoryListItemsViewModel categoryListItemsViewModel;
    private OrderItemsListViewModel orderItemsListViewModel;

    private WaiterViewModel waiterViewModel;
    private ChefLogInViewModel chefLogInViewModel;
    private ChefViewModel chefViewModel;

    public ViewModelProvider(ViewHandler viewHandler, LogInModel model){
        this.mainModel=model;
        this.viewHandler = viewHandler;
        instantiateViewModels();
    }

    public void instantiateViewModels()
    {
        customerModel= mainModel.getCustomerModel();
        waiterModel = mainModel.getWaiterModel();
        chefModel = mainModel.getChefModel();

        System.out.println("customer model, provider: "+customerModel);

        onOpenViewModel= new OnOpenViewModel(mainModel, viewHandler);

        menuFrontViewModel= new MenuFrontViewModel(customerModel);
        categoryListItemsViewModel = new CategoryListItemsViewModel(viewHandler, customerModel);
        categoryListViewModel = new CategoryListViewModel(viewHandler, customerModel);
        orderItemsListViewModel = new OrderItemsListViewModel(customerModel);

        waiterViewModel = new WaiterViewModel(waiterModel);

        chefLogInViewModel = new ChefLogInViewModel(viewHandler, chefModel);
        chefViewModel= new ChefViewModel(chefModel);
    }

    public ViewModels getViewModel(Views viewToOpen) {
        switch(viewToOpen)
        {
            case ON_OPEN:{
                return onOpenViewModel;
            }
            case MENU_FRONT:{
                return menuFrontViewModel;
            }
            case MENU_FRONT_LABEL:{
                return menuFrontViewModel;
            }
            case CATEGORIES:{
                return categoryListViewModel;
            }
            case ITEMS:{
                return categoryListItemsViewModel;
            }
            case ORDER:{
                return orderItemsListViewModel;
            }
            case WAITER:{
                return waiterViewModel;
            }
            case CHEF_FRONT:{
                return chefViewModel;
            }
            case CHEF_LOG_IN:{
                return chefLogInViewModel;
            }
            default:
                return null;
        }
    }
}
