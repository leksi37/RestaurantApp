package client.viewModel;

import BasicClasses.Views;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;

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



    public ViewModelProvider(CustomerModel model){
        this.model=model;
        instantiateViewModels();
    }
    public ViewModelProvider(ChefModel model){
        this.chefModel=model;
        instantiateViewModels();
    }

    public void instantiateViewModels() {
        menuFrontViewModel= new MenuFrontViewModel(model);
        categoryListItemsViewModel = new CategoryListItemsViewModel(model);
        categoryListViewModel = new CategoryListViewModel(model);
        orderItemsListViewModel = new OrderItemsListViewModel(model);
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
