package client.view;

import BasicClasses.Views;
import BasicClasses.type;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.view.OnOpen.OnOpen;
import client.view.chef.Chef;
import client.view.customer.categoryList.CategoryList;
import client.view.customer.categoryListItems.CategoryListItems;
import client.view.customer.menuFront.MenuFront;
import client.view.customer.orderItemsList.OrderItemList;
import client.viewModel.Chef.ChefViewModel;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.CategoryListItemsViewModel;
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
    private Chef chef;
    private OnOpen onOpen;

    public ViewHandler(Stage stage, CustomerModel model){
        this.stage=stage;
        this.viewModelProvider=new ViewModelProvider(model);

        viewModelProvider.instantiateViewModels();
    }

    public ViewHandler(Stage stage, ChefModel chefModel){
        this.stage=stage;
        this.viewModelProvider=new ViewModelProvider(chefModel);
        viewModelProvider.instantiateViewModels();
    }

    public void openView(Views viewToOpen)
    {
        Scene scene= null;
        FXMLLoader loader= new FXMLLoader();
        Parent root= null;
        switch(viewToOpen)
        {
            case ON_OPEN:
            {
                loader.setLocation(getClass().getResource("OnOpen/onOpen.fxml"));
                try{
                    root=loader.load();
                }
                catch(IOException e){e.printStackTrace();}

                onOpen = loader.getController();
                onOpen.init(this);
                break;
            }
            case MENU_FRONT:
            {
                loader.setLocation(getClass().getResource("customer/menuFront/MenuFront.fxml"));
                try{
                    root=loader.load();
                }
                catch(IOException e){e.printStackTrace();}

                menuFront = loader.getController();
                menuFront.init(this);
                break;
            }
            case MENU_FRONT_LABEL:
            {
                loader.setLocation(getClass().getResource("customer/menuFront/MenuFront.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                menuFront= loader.getController();
                menuFront.init(this);
                menuFront.setOrderStatus("Your order is now being prepared.");
                break;
            }
            case CATEGORIES:
            {
                loader.setLocation(getClass().getResource("customer/categoryList/CategoryList.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                categoryList= loader.getController();
                categoryList.init(this);
                break;
            }
            case ITEMS:
            {
                loader.setLocation(getClass().getResource("customer/categoryListItems/CategoryListItems.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                categoryListItems= loader.getController();
                categoryListItems.init((CategoryListItemsViewModel) viewModelProvider.getViewModel(viewToOpen), categoryToOpen, this);
                break;
            }
            case ORDER:
            {
                loader.setLocation(getClass().getResource("customer/orderItemsList/OrderItemList.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                orderItemList= loader.getController();
                orderItemList.init((OrderItemsListViewModel) viewModelProvider.getViewModel(viewToOpen), this);
                break;
            }
            case CHEF_FRONT:
            {
                loader.setLocation(getClass().getResource("chef/chef.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                chef= loader.getController();
                chef.init((ChefViewModel) viewModelProvider.getViewModel(viewToOpen), this);
                break;
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
        openView(Views.ITEMS);
    }

}
