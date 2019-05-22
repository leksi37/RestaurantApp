package client.view;

import basicClasses.Views;
import basicClasses.type;
import client.model.chef.ChefModel;
import client.model.customer.CustomerModel;
import client.model.logIn.LogInModel;
import client.view.OnOpen.OnOpen;
import client.view.chef.Chef;
import client.view.chef.LogInChef;
import client.view.customer.categoryList.CategoryList;
import client.view.customer.categoryListItems.CategoryListItems;
import client.view.customer.menuFront.MenuFront;
import client.view.customer.orderItemsList.OrderItemList;
import client.viewModel.Chef.ChefLogInViewModel;
import client.viewModel.Chef.ChefViewModel;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.CategoryListItemsViewModel;
import client.viewModel.customer.CategoryListViewModel;
import client.viewModel.customer.MenuFrontViewModel;
import client.viewModel.customer.OrderItemsListViewModel;
import client.viewModel.logIn.OnOpenViewModel;
import javafx.application.Platform;
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
    private LogInChef logInchef;

    public ViewHandler(Stage stage, LogInModel model){
        this.stage=stage;
        this.viewModelProvider=new ViewModelProvider(this, model);

        viewModelProvider.instantiateViewModels();
    }

    public void openView(Views viewToOpen)
    {
        FXMLLoader loader= new FXMLLoader();

        Platform.runLater(() ->{
        Parent root= null;
        switch(viewToOpen)
        {
            case ON_OPEN:
            {
                loader.setLocation(getClass().getResource("OnOpen/onOpen.fxml"));
                try{
                    root=loader.load();
                    System.out.println("root: "+root);
                }
                catch(IOException e){e.printStackTrace();}

                onOpen = loader.getController();
                onOpen.init((OnOpenViewModel)viewModelProvider.getViewModel(viewToOpen));
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
                menuFront.init((MenuFrontViewModel) viewModelProvider.getViewModel(viewToOpen),this);
                break;
            }
            case MENU_FRONT_LABEL:
            {
                loader.setLocation(getClass().getResource("customer/menuFront/MenuFront.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                menuFront= loader.getController();
                menuFront.init((MenuFrontViewModel) viewModelProvider.getViewModel(viewToOpen),this);
                menuFront.setOrderStatus("Your order is now being prepared.");
                break;
            }
            case CATEGORIES:
            {
                loader.setLocation(getClass().getResource("customer/categoryList/CategoryList.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                categoryList= loader.getController();
                categoryList.init((CategoryListViewModel) viewModelProvider.getViewModel(viewToOpen),this);
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
            case CHEF:
            {
                loader.setLocation(getClass().getResource("chef/chef.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                chef= loader.getController();
                chef.init((ChefViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }
            case CHEF_LOG_IN:
            {
                loader.setLocation(getClass().getResource("chef/logInChef.fxml"));
                try{ root=loader.load();}
                catch(IOException e){e.printStackTrace();}

                logInchef= loader.getController();
                logInchef.init((ChefLogInViewModel) viewModelProvider.getViewModel(viewToOpen));
                break;
            }
        }

        stage.setTitle("MLP");
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        });

    }


    public void setCategory(type category)
    {
        categoryToOpen = category;
        openView(Views.ITEMS);
    }

}
