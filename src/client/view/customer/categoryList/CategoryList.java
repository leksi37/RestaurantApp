package client.view.customer.categoryList;

import BasicClasses.type;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.CategoryListViewModel;

public class CategoryList {

    private CategoryListViewModel viewModel;

    public void openAppetizers() {
        viewModel.openCategoryListItems(type.appetizers);
    }

    public void openDrinks() {
        viewModel.openCategoryListItems(type.nonAlcoholic);
    }
    public void openAlcohol() {
        viewModel.openCategoryListItems(type.alcohol);
    }
    public void openSalads() {
        System.out.println("view");
        viewModel.openCategoryListItems(type.salads);
    }

    public void openDesserts() {
        viewModel.openCategoryListItems(type.dessert);
    }

    public void openBreakfast() {
        viewModel.openCategoryListItems(type.breakfast);
    }

    public void openPasta() {
        viewModel.openCategoryListItems(type.pasta);
    }

    public void openPizza() {
        viewModel.openCategoryListItems(type.pizza);
    }

    public void openSoup() {
        viewModel.openCategoryListItems(type.soup);
    }

    public void openSideDish() {
        viewModel.openCategoryListItems(type.sideDish);
    }

    public void init(CategoryListViewModel vm){
        viewModel = vm;
    }
}
