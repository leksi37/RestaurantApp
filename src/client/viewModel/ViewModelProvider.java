package client.viewModel;

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

    //viewModels
    private MenuFrontViewModel menuFrontViewModel;
    private CategoryListViewModel categoryListViewModel;
    private CategoryListItemsViewModel categoryListItemsViewModel;
    private OrderItemsListViewModel orderItemsListViewModel;



    public ViewModelProvider(CustomerModel model){
        this.model=model;
    }

    public void instantiateViewModels(ViewHandler viewHan) {
        this.viewHandler=viewHan;
        menuFrontViewModel= new MenuFrontViewModel(model, viewHandler);
        categoryListItemsViewModel = new CategoryListItemsViewModel(model, viewHandler);
        categoryListViewModel = new CategoryListViewModel(model, viewHandler);
        orderItemsListViewModel = new OrderItemsListViewModel(model,viewHandler);
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
}
