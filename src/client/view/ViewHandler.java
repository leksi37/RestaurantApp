package client.view;

import BasicClasses.Order;
import client.model.customer.CustomerModel;
import client.view.customer.categoryList.CategoryList;
import client.view.customer.categoryListItems.CategoryListItems;
import client.view.customer.menuFront.MenuFront;
import client.view.customer.orderItemsList.OrderItemList;
import client.viewModel.MenuProxy;
import client.viewModel.ViewModelProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewHandler {

    private ViewModelProvider viewModelProvider;
    private Stage stage;

    public ViewHandler(Stage stage, CustomerModel model){
        this.stage=stage;
        this.viewModelProvider=new ViewModelProvider(model);
        viewModelProvider.instantiateViewModels(this);
    }


    public void openMenuFront(){
        Scene scene= null;
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("customer/menuFront/MenuFront.fxml"));
        Parent root= null;

        try{ root=loader.load();}
        catch(IOException e){e.printStackTrace();}

        MenuFront view= loader.getController();
        view.init(viewModelProvider);

        scene= new Scene(root);
        stage.setTitle("R E S T A U R A N T ");
        stage.setScene(scene);
        stage.show();
    }

    public void openCategoryList() {
        viewModelProvider.instantiateViewModels(this);
        Scene scene= null;
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("customer/categoryList/CategoryList.fxml"));
        Parent root= null;

        try{ root=loader.load();}
        catch(IOException e){e.printStackTrace();}

        CategoryList view= loader.getController();
        view.init(viewModelProvider);

        scene= new Scene(root);
        stage.setTitle("Categories");
        stage.setScene(scene);
        stage.show();
    }


    public void openCategoryListItems(String name){
        Scene scene= null;
        FXMLLoader loader= new FXMLLoader();
        Parent root= null;
        loader.setLocation(getClass().getResource("customer/categoryListItems/CategoryListItems.fxml"));


        try{ root=loader.load();}
        catch(IOException e){
            e.printStackTrace();
            System.out.println("cannot load");
        }

        CategoryListItems view= loader.getController();
        view.init(viewModelProvider);
        view.setMenu(name);

        stage.setTitle(name.toUpperCase());
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openOrderItemsList(Order order){
        Scene scene= null;
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("customer/orderItemsList/OrderItemList.fxml"));
        Parent root= null;

        try{ root=loader.load();}
        catch(IOException e){e.printStackTrace();}

        OrderItemList view= loader.getController();
        view.init(viewModelProvider);

        scene= new Scene(root);
        stage.setTitle("Order");
        stage.setScene(scene);
        stage.show();
    }
}
