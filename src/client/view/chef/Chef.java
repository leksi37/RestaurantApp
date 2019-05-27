package client.view.chef;

import basicClasses.ItemQuantity;
import basicClasses.ItemState;
import basicClasses.Order;
import client.view.ViewHandler;
import client.viewModel.Chef.ChefViewModel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;

public class Chef {
    private ChefViewModel viewModel;

    @FXML
    private VBox vBox;
    @FXML
    private ListView orderList;
    @FXML
    private TextArea note;

    private int lastSelected;

    public void init(ChefViewModel v) {
        this.viewModel = v;
        lastSelected = 0;
        viewModel.addListener("refresh", this :: refresh);
        System.out.println("chef view");
        viewModel.fetchOrders();
        orderList.setItems(viewModel.getOrders());
        orderList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(orderList.getSelectionModel().getSelectedIndex() >= 0)
                {
                    lastSelected = orderList.getSelectionModel().getSelectedIndex();
                    System.out.println("last selected " + lastSelected + " calling from the changing code ");
                    Platform.runLater(() ->
                        viewItems()
                    );
                }

            }
        });
    }

    private void refresh(PropertyChangeEvent propertyChangeEvent) {
            System.out.println("calling from refresh");
//            orderList.getSelectionModel().select(lastSelected);
        Platform.runLater(() ->
                viewItems()
                );
    }

    private void viewItems()
    {
        System.out.println("clearing");
        vBox.getChildren().clear();
        Order order = viewModel.getOrder(lastSelected);
        note.setText(order.getNote());
        System.out.println("chef view, number of items in order " + order.getNumberOfItems());
        for(int i = 0; i < order.getNumberOfItems(); ++i)
        {
            ItemQuantity iq = order.getItemWithQuantity(i);
            HBox oneItem = new HBox();
            VBox itemDetails = new VBox();
            HBox left = new HBox();
            HBox right = new HBox();
            Label name = new Label(iq.getItem().getName() + " x " + iq.getQuantity());;
            Label description = new Label(iq.getItem().getDescription());
            Button button = new Button();
            button.setId(iq.getId());
            button.setText(viewModel.getButtonText(iq.getId(), lastSelected).getValue());
            if(button.getText().equals("Selected for waiter"))
                button.setDisable(true);
            button.setOnAction(event -> {
                viewModel.nextState(button.getId(), lastSelected);
//                if(button.getText().equals("Start"))
//                {
//                    viewModel.itemStarted(button.getId(), orderList.getSelectionModel().getSelectedIndex());
//                    button.setText("In progress");
//                }
//                else if(button.getText().equals("In progress"))
//                {
//                    viewModel.itemDone(button.getId(), orderList.getSelectionModel().getSelectedIndex());
//                    button.setText("Send to waiter");
//                }
//                else if(button.getText().equals("Send to waiter"))
//                {
//                    viewModel.itemAddedToPartialOrder(button.getId(), orderList.getSelectionModel().getSelectedIndex());
//                    button.setText("Selected");
//                    button.setDisable(true);
//                }
            });
            Platform.runLater(() -> {
                itemDetails.getChildren().add(name);
                itemDetails.getChildren().add(description);
                left.getChildren().add(itemDetails);
                left.setStyle("-fx-pref-width: 200px");
                right.getChildren().add(button);
                right.setAlignment(Pos.BASELINE_RIGHT);
                right.setStyle("-fx-pref-width: 150px");
                oneItem.getChildren().add(left);
                oneItem.getChildren().add(right);
                vBox.getChildren().add(oneItem);
                oneItem.setStyle("-fx-padding: 15px; -fx-border-width: 0 0 2px 0; -fx-border-color:  #ffcccc");
            });

        }
    }

    @FXML
    private void sendPartial() {
        viewModel.sendPartial((int)orderList.getSelectionModel().getSelectedIndex());
    }

}
