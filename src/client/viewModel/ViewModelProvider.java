package client.viewModel;

import BasicClasses.Views;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;

import javax.swing.text.View;

public class ViewModelProvider {

    private ViewHandler viewHandler;

    //models
    private CustomerModel model;
    private ChefModel chefModel;
    //viewModels
    private MenuFrontViewModel menuFrontViewModel;
    private CategoryListViewModel categoryListViewModel;
    private CategoryListItemsViewModel categoryListItemsViewModel;
    private OrderItemsListViewModel orderItemsListViewModel;

    public ViewModelProvider(ViewHandler viewHandler, CustomerModel model){
        this.model=model;
        this.viewHandler = viewHandler;
        instantiateViewModels();
    }
    public ViewModelProvider(ViewHandler viewHandler, ChefModel model){
        this.viewHandler = viewHandler;
        this.chefModel=model;
        instantiateViewModels();
    }

    public void instantiateViewModels() {
        menuFrontViewModel= new MenuFrontViewModel(model);
        categoryListItemsViewModel = new CategoryListItemsViewModel(viewHandler, model);
        categoryListViewModel = new CategoryListViewModel(viewHandler, model);
        orderItemsListViewModel = new OrderItemsListViewModel(model);
    }

    public ViewModels getViewModel(Views viewToOpen) {
        switch(viewToOpen)
        {
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
            default:
                return null;
        }
    }
}
