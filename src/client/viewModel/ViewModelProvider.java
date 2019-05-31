package client.viewModel;

import basicClasses.Views;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.model.logIn.LogInModel;
import client.model.waiter.WaiterModel;
import client.view.ViewHandler;
import client.viewModel.chef.ChefViewModel;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;
import client.viewModel.logIn.LogInViewModel;
import client.viewModel.logIn.OnOpenViewModel;
import client.viewModel.waiter.WaiterViewModel;

public class ViewModelProvider {

    private ViewHandler viewHandler;

    //models
    private LogInModel mainModel;
    private CustomerModel customerModel;
    private ChefModel chefModel;
    private WaiterModel waiterModel;

    //viewModels
    private OnOpenViewModel onOpenViewModel;
    private LogInViewModel logInViewModel;

    private MenuFrontViewModel menuFrontViewModel;
    private CategoryListViewModel categoryListViewModel;
    private CategoryListItemsViewModel categoryListItemsViewModel;
    private OrderItemsListViewModel orderItemsListViewModel;

    private WaiterViewModel waiterViewModel;

    private ChefViewModel chefViewModel;


    public ViewModelProvider(ViewHandler viewHandler, LogInModel model){
        this.mainModel=model;
        this.viewHandler = viewHandler;
        //instantiateViewModels();
    }

    public void instantiateViewModels()
    {
        customerModel= mainModel.getCustomerModel();
        chefModel = mainModel.getChefModel();
        waiterModel = mainModel.getWaiterModel();

        onOpenViewModel= new OnOpenViewModel(mainModel, viewHandler);
        logInViewModel = new LogInViewModel(viewHandler, mainModel);

        menuFrontViewModel= new MenuFrontViewModel(customerModel);
        categoryListItemsViewModel = new CategoryListItemsViewModel(viewHandler, customerModel);
        categoryListViewModel = new CategoryListViewModel(viewHandler, customerModel);
        orderItemsListViewModel = new OrderItemsListViewModel(customerModel);

        waiterViewModel = new WaiterViewModel(waiterModel);

        chefViewModel = new ChefViewModel(chefModel);
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
            case CHEF_LOG_IN:{
                return logInViewModel;
            }
            case CHEF:{
                return chefViewModel;
            }
            case WAITER_LOG_IN:{
                return logInViewModel;
            }
            case WAITER: {
                return waiterViewModel;
            }
            default:
                return null;
        }
    }
}
