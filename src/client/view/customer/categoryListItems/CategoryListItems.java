package client.view.customer.categoryListItems;

import BasicClasses.MenuItem;
import BasicClasses.type;
import client.viewModel.ViewModelProvider;
import client.viewModel.customer.CategoryListItemsViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class CategoryListItems {
    private CategoryListItemsViewModel categoryListItemsViewModel;
    private ObservableList<Integer> quantityOfItem = FXCollections.observableArrayList(1,2,3,4,5, 6, 7, 8, 9, 10, 11);

    @FXML
    private Button backButton;
    @FXML
    private VBox list;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button goToOrder;

    public void init(CategoryListItemsViewModel vm, type category) {
        categoryListItemsViewModel = vm;
        categoryListItemsViewModel.addListener("gotItems", this::setItems);
        scrollPane = new ScrollPane();
        categoryListItemsViewModel.getItems(category.name());
//        list = new VBox();
        scrollPane.vvalueProperty().bind(list.heightProperty());
    }

    public void back()
    {
        categoryListItemsViewModel.back();
    }


    private void setItems(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<MenuItem> items = (ArrayList<MenuItem>) propertyChangeEvent.getNewValue();
        for(int i = 0; i < items.size(); ++i)
        {
//            System.out.println(items.get(i));
            HBox box = new HBox();
            box.setId(items.get(i).getId());

            VBox left  = new VBox();
            VBox right  = new VBox();

            Label name = new Label((String)items.get(i).getName());
            Label price = new Label("" + items.get(i).getPrice() + "kr");
            name.setStyle("-fx-font-size: 30;");

            left.getChildren().add(name);
            Label description = new Label((String)items.get(i).getDescription());
            left.getChildren().add(description);
            right.getChildren().add(price);

            Button minus = new Button("-");
            Label quantity = new Label("0");
            Button plus = new Button("+");

            HBox quantityBox = new HBox();
            quantityBox.getChildren().add(minus);
            quantityBox.getChildren().add(quantity);
            quantityBox.getChildren().add(plus);
            quantityBox.setAlignment(Pos.BASELINE_RIGHT);
            right.getChildren().add(quantityBox);
            left.setStyle("-fx-pref-width: 350;");
            right.setStyle("-fx-pref-width: 186;");
            right.setAlignment(Pos.BASELINE_RIGHT);

            plus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int k = Integer.parseInt(quantity.getText());
                    k++;
                    categoryListItemsViewModel.addToOrder(box.getId(), 1);
                    quantity.setText("" + k);
                }
            });

            minus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int k = Integer.parseInt(quantity.getText());
                    if(k > 0) {
                        categoryListItemsViewModel.removeFromOrder(box.getId(), 1);
                        --k;
                    }
                    quantity.setText("" + k);
                }
            });

            Platform.runLater(()->{
                box.getChildren().add(left);
                box.getChildren().add(right);
                list.getChildren().add(box);
            });

        }
    }


    public void setMenu(String type) {
        categoryListItemsViewModel.getItems(type);
    }

    public void seeOrder(ActionEvent actionEvent) {
        categoryListItemsViewModel.seeOrder();
    }
}