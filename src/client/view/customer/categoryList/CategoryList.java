package client.view.customer.categoryList;

import basicClasses.CategoryType;
import client.view.ViewHandler;
import client.viewModel.customer.CategoryListViewModel;
import javafx.event.ActionEvent;

public class CategoryList {

    private CategoryListViewModel categoryListViewModel;


    public void openAppetizers() { categoryListViewModel.openCategory(CategoryType.appetizers); }

    public void openDrinks() {
        categoryListViewModel.openCategory(CategoryType.nonAlcoholic);
    }

    public void openAlcohol() {
        categoryListViewModel.openCategory(CategoryType.alcohol);
    }

    public void openSalads() {
        categoryListViewModel.openCategory(CategoryType.salads);
    }

    public void openDesserts() {
        categoryListViewModel.openCategory(CategoryType.dessert);
    }

    public void openBreakfast() {
        categoryListViewModel.openCategory(CategoryType.breakfast);
    }

    public void openPasta() {
        categoryListViewModel.openCategory(CategoryType.pasta);
    }

    public void openPizza() {
        categoryListViewModel.openCategory(CategoryType.pizza);
    }

    public void openSoup() {
        categoryListViewModel.openCategory(CategoryType.soup);
    }

    public void openSideDish() {
        categoryListViewModel.openCategory(CategoryType.sideDish);
    }


    public void init(CategoryListViewModel vm){
        this.categoryListViewModel = vm;
    }

    public void back(ActionEvent actionEvent) {
        categoryListViewModel.backToMenuFront();
    }
}
