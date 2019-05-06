package client.viewModel;

import BasicClasses.Views;
import client.model.customer.CustomerModel;
import client.view.ViewHandler;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;

public class ViewModelProvider {

//    private ViewHandler viewHandler;

    //models
    private CustomerModel model;

    //viewModels
    private MenuFrontViewModel menuFrontViewModel;
    private CategoryListViewModel categoryListViewModel;
    private CategoryListItemsViewModel categoryListItemsViewModel;
    private OrderItemsListViewModel orderItemsListViewModel;



    public ViewModelProvider(CustomerModel model){
        this.model=model;
    }

    public void instantiateViewModels(ViewHandler viewHandler) {
//        this.viewHandler=viewHandler;
        menuFrontViewModel= new MenuFrontViewModel(model, viewHandler);
        categoryListItemsViewModel = new CategoryListItemsViewModel(model, viewHandler);
        categoryListViewModel = new CategoryListViewModel(model, viewHandler);
        orderItemsListViewModel = new OrderItemsListViewModel(model,viewHandler);
    }

//    public MenuFrontViewModel getMenuFrontViewModel() {
//        return menuFrontViewModel;
//    }
//
//    public CategoryListViewModel getCategoryListViewModel() {
//        return categoryListViewModel;
//    }
//
//    public CategoryListItemsViewModel getCategoryListItemsViewModel() {
//        return categoryListItemsViewModel;
//    }
//
//    public OrderItemsListViewModel getOrderItemsListViewModel() {
//        return orderItemsListViewModel;
//    }

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
            default:
                return null;
        }
    }
}
