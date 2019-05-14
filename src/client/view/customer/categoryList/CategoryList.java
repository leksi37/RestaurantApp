package client.view.customer.categoryList;

import BasicClasses.type;
import client.view.ViewHandler;
import client.viewModel.customer.CategoryListViewModel;

public class CategoryList {

    private ViewHandler viewHandler;
    private CategoryListViewModel categoryListViewModel;

    public void openAppetizers() {
        categoryListViewModel.openCategory(type.appetizers);
        viewHandler.setCategory(type.appetizers);
    }

    public void openDrinks() {
        categoryListViewModel.openCategory(type.nonAlcoholic);
    }
    public void openAlcohol() {
        categoryListViewModel.openCategory(type.alcohol);
    }
    public void openSalads() {
        categoryListViewModel.openCategory(type.salads);
    }

    public void openDesserts() {
        categoryListViewModel.openCategory(type.dessert);
    }

    public void openBreakfast() {
        categoryListViewModel.openCategory(type.breakfast);
    }

    public void openPasta() {
        categoryListViewModel.openCategory(type.pasta);
    }

    public void openPizza() {
        categoryListViewModel.openCategory(type.pizza);
    }

    public void openSoup() {
        categoryListViewModel.openCategory(type.soup);
    }

    public void openSideDish() {
        categoryListViewModel.openCategory(type.sideDish);
    }

    public void init(CategoryListViewModel vm, ViewHandler viewHandler){
        this.viewHandler = viewHandler;
        this.categoryListViewModel = vm;
    }
}
