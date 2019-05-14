package client.view;

import BasicClasses.Order;
import BasicClasses.Views;
import BasicClasses.type;
import client.model.customer.CustomerModel;
import client.model.logIn.LogInModel;
import client.view.customer.categoryList.CategoryList;
import client.view.customer.categoryListItems.CategoryListItems;
import client.view.customer.menuFront.MenuFront;
import client.view.customer.orderItemsList.OrderItemList;
import client.view.logIn.LogIn;
import client.viewModel.MenuProxy;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;
import client.viewModel.logIn.LogInViewModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ViewHandler {

    private ViewModelProvider viewModelProvider;
    private Stage stage;
    private type categoryToOpen;

    public ViewHandler(Stage stage,LogInModel logInModel){
        this.stage=stage;
        this.viewModelProvider=new ViewModelProvider(logInModel);
        viewModelProvider.instantiateViewModels(this);
    }


//    public void openMenuFront(){
//        Scene scene= null;
//        FXMLLoader loader= new FXMLLoader();
//        loader.setLocation(getClass().getResource("customer/menuFront/MenuFront.fxml"));
//        Parent root= null;
//
//        try{ root=loader.load();}
//        catch(IOException e){e.printStackTrace();}
//
//        MenuFront view= loader.getController();
//        view.init(viewModelProvider);
//
//        scene= new Scene(root);
//        stage.setTitle("R E S T A U R A N T ");
//        stage.setScene(scene);
//        stage.show();
//    }

//    public void openCategoryList() {
////        viewModelProvider.instantiateViewModels(this);
//        Scene scene= null;
//        FXMLLoader loader= new FXMLLoader();
//        loader.setLocation(getClass().getResource("customer/categoryList/CategoryList.fxml"));
//        Parent root= null;
//
//        try{ root=loader.load();}
//        catch(IOException e){e.printStackTrace();}
//
//        CategoryList view= loader.getController();
//        view.init(viewModelProvider);
//
//        scene= new Scene(root);
//        stage.setTitle("Categories");
//        stage.setScene(scene);
//        stage.show();
//    }


    public void openView(Views viewToOpen)
    {
        Scene scene= null;
        FXMLLoader loader= new FXMLLoader();
        Parent root= null;
        switch(viewToOpen)
        {
            case MENU_FRONT:
            {
                loader.setLocation(getClass().getResource("customer/menuFront/MenuFront.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                MenuFront view= loader.getController();
                view.init((MenuFrontViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }
            case CATEGORIES:
            {
                loader.setLocation(getClass().getResource("customer/categoryList/CategoryList.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                CategoryList view= loader.getController();
                view.init((CategoryListViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }
            case ITEMS:
            {
                loader.setLocation(getClass().getResource("customer/categoryListItems/CategoryListItems.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                CategoryListItems view= loader.getController();
                view.init((CategoryListItemsViewModel) viewModelProvider.getViewModel(viewToOpen), categoryToOpen);
                break;
            }
            case ORDER:
            {
                loader.setLocation(getClass().getResource("customer/orderItemsList/OrderItemList.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                OrderItemList view= loader.getController();
                view.init((OrderItemsListViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }
            case LOGIN:
            {
                loader.setLocation(getClass().getResource("logIn/LogIn.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                LogIn view= loader.getController();
                view.init((LogInViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }

            // TO BE ADDED !!!!!!!

            case CHEF:{

            }
            case WAITER:{

            }
            case ADMIN:{

            }
        }


        scene= new Scene(root);
        stage.setTitle("MLP");
        stage.setScene(scene);
        stage.show();
    }


    public void setCategory(type category)
    {
        categoryToOpen = category;
    }




//    public void openCategoryListItems(String name){
//        Scene scene= null;
//        FXMLLoader loader= new FXMLLoader();
//        Parent root= null;
//        loader.setLocation(getClass().getResource("customer/categoryListItems/CategoryListItems.fxml"));
//
//
//        try{ root=loader.load();}
//        catch(IOException e){
//            e.printStackTrace();
//            System.out.println("cannot load");
//        }
//
//        CategoryListItems view= loader.getController();
//        view.init(viewModelProvider);
//        view.setMenu(name);
//
//        stage.setTitle(name.toUpperCase());
//        scene= new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

//    public void openOrderItemsList(Order order){
//        Scene scene= null;
//        FXMLLoader loader= new FXMLLoader();
//        loader.setLocation(getClass().getResource("customer/orderItemsList/OrderItemList.fxml"));
//        Parent root= null;
//
//        try{ root=loader.load();}
//        catch(IOException e){e.printStackTrace();}
//
//        OrderItemList view= loader.getController();
//        view.init(viewModelProvider.getOrderItemsListViewModel());
//
//        scene= new Scene(root);
//        stage.setTitle("Order");
//        stage.setScene(scene);
//        stage.show();
//    }
}
