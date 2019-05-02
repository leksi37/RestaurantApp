package client.view.customer.categoryList;

import client.viewModel.ViewModelProvider;
import client.viewModel.customer.CategoryListViewModel;

public class CategoryList {

    private CategoryListViewModel viewModel;

    public void openAppetizers() {
        viewModel.openCategoryListItems("appetizers");
    }

    public void openDrinks() {
        viewModel.openCategoryListItems("nonAlcoholic");
    }
    public void openAlcohol() {
        viewModel.openCategoryListItems("alcohol");
    }
    public void openSalads() {
        viewModel.openCategoryListItems("salads");
    }

    public void openDesserts() {
        viewModel.openCategoryListItems("dessert");
    }

    public void openBreakfast() {
        viewModel.openCategoryListItems("breakfast");
    }

    public void openPasta() {
        viewModel.openCategoryListItems("pasta");
    }

    public void openPizza() {
        viewModel.openCategoryListItems("pizza");
    }

    public void openSoup() {
        viewModel.openCategoryListItems("soup");
    }

    public void openSideDish() {
        viewModel.openCategoryListItems("sideDish");
    }

    public void init(CategoryListViewModel vm){
        viewModel = vm;
    }
}
