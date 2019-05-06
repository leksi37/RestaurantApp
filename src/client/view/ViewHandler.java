package client.view;

import BasicClasses.Views;
import BasicClasses.type;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.view.customer.categoryList.CategoryList;
import client.view.customer.categoryListItems.CategoryListItems;
import client.view.customer.menuFront.MenuFront;
import client.view.customer.orderItemsList.OrderItemList;
import client.view.waiter.frontView.FrontView;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private ViewModelProvider viewModelProvider;
    private Stage stage;
    private type categoryToOpen;
    private MenuFront menuFront;
    private CategoryList categoryList;
    private CategoryListItems categoryListItems;
    private OrderItemList orderItemList;

    public ViewHandler(Stage stage, CustomerModel model){
        instantiateViews();
        this.stage=stage;
        this.viewModelProvider=new ViewModelProvider(model);

        viewModelProvider.instantiateViewModels();
    }

    public ViewHandler(Stage stage, ChefModel chefModel){
        this.stage=stage;
        this.viewModelProvider=new ViewModelProvider(chefModel);
        viewModelProvider.instantiateViewModels();
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



    public void instantiateViews(){
       menuFront = new MenuFront(this);
       categoryList = new CategoryList(this);
       categoryListItems = new CategoryListItems(this);
       orderItemList = new OrderItemList(this);
    }

    public MenuFront getMenuFront(){
        return menuFront;
    }

    public CategoryList getCategoryList(){
        return categoryList;
    }

    public CategoryListItems getCategoryListItems(){
        return categoryListItems;
    }

    public OrderItemList getOrderItemList(){
        return orderItemList;
    }

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
                try{
                    root=loader.load();
                }
                catch(IOException e){e.printStackTrace();}

                menuFront = loader.getController();
                menuFront.init((MenuFrontViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }
            case MENU_FRONT_LABEL:
            {
                loader.setLocation(getClass().getResource("customer/menuFront/MenuFront.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                menuFront= loader.getController();
                menuFront.init((MenuFrontViewModel) viewModelProvider.getViewModel(viewToOpen));
                menuFront.setOrderStatus("Your order is now being prepared.");
                break;
            }
            case CATEGORIES:
            {
                loader.setLocation(getClass().getResource("customer/categoryList/CategoryList.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                categoryList= loader.getController();
                categoryList.init((CategoryListViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }
            case ITEMS:
            {
                loader.setLocation(getClass().getResource("customer/categoryListItems/CategoryListItems.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                categoryListItems= loader.getController();
                categoryListItems.init((CategoryListItemsViewModel) viewModelProvider.getViewModel(viewToOpen), categoryToOpen);
                break;
            }
            case ORDER:
            {
                loader.setLocation(getClass().getResource("customer/orderItemsList/OrderItemList.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                orderItemList= loader.getController();
                orderItemList.init((OrderItemsListViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }
            case CHEF_FRONT:
            {
                loader.setLocation(getClass().getResource("chef/chef.fxml"));
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
        System.out.println("category is " + category);
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
