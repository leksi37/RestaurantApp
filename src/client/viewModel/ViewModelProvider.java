package client.viewModel;

import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.model.logIn.LogInModel;
import client.view.ViewHandler;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;
import client.viewModel.logIn.LogInViewModel;

public class ViewModelProvider {

    private ViewHandler viewHandler;

    //models
    private CustomerModel model;
    private LogInModel logInModel;

    //viewModels
    private MenuFrontViewModel menuFrontViewModel;
    private CategoryListViewModel categoryListViewModel;
    private CategoryListItemsViewModel categoryListItemsViewModel;
    private OrderItemsListViewModel orderItemsListViewModel;
    private LogInViewModel logInViewModel;


    public ViewModelProvider(LogInModel logInModel){
        this.logInModel= logInModel;
    }

    public void instantiateViewModels(ViewHandler viewHandler) {
        this.viewHandler=viewHandler;
        this.model=logInModel.getCustomerModel();

        menuFrontViewModel= new MenuFrontViewModel(model, viewHandler);
        categoryListItemsViewModel = new CategoryListItemsViewModel(model, viewHandler);
        categoryListViewModel = new CategoryListViewModel(model, viewHandler);
        orderItemsListViewModel = new OrderItemsListViewModel(model,viewHandler);

        logInViewModel=new LogInViewModel(logInModel, viewHandler);
    }

    public MenuFrontViewModel getMenuFrontViewModel() {
        return menuFrontViewModel;
    }

    public CategoryListViewModel getCategoryListViewModel() {
        return categoryListViewModel;
    }

    public CategoryListItemsViewModel getCategoryListItemsViewModel() {
        return categoryListItemsViewModel;
    }

    public OrderItemsListViewModel getOrderItemsListViewModel() {
        return orderItemsListViewModel;
    }

    public LogInViewModel getLogInViewModel(){return logInViewModel;}

    public ViewModels getViewModel(Views viewToOpen) {
        switch(viewToOpen)
        {
            case MENU_FRONT:{
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
            case LOGIN:{
                return logInViewModel;
            }



            default:
                return null;
        }
    }
}
