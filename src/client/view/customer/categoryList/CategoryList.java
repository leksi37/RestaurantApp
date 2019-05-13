package client.view.customer.categoryList;

import BasicClasses.type;
import client.view.ViewHandler;
import client.viewModel.customer.CategoryListViewModel;

import javax.swing.text.View;

public class CategoryList {

    private ViewHandler viewHandler;

    public void openAppetizers() {
        viewHandler.setCategory(type.appetizers);
    }

    public void openDrinks() {
        viewHandler.setCategory(type.nonAlcoholic);
    }
    public void openAlcohol() {
        viewHandler.setCategory(type.alcohol);
    }
    public void openSalads() {
        System.out.println("view");
        viewHandler.setCategory(type.salads);
    }

    public void openDesserts() {
        viewHandler.setCategory(type.dessert);
    }

    public void openBreakfast() {
        viewHandler.setCategory(type.breakfast);
    }

    public void openPasta() {
        viewHandler.setCategory(type.pasta);
    }

    public void openPizza() {
        viewHandler.setCategory(type.pizza);
    }

    public void openSoup() {
        viewHandler.setCategory(type.soup);
    }

    public void openSideDish() {
        viewHandler.setCategory(type.sideDish);
    }

    public void init( ViewHandler viewHandler){
        this.viewHandler = viewHandler;
    }
}
