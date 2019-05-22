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

    @FXML
    private void sendPartial(){

    }

    @FXML
    private void closeOrder(){

    }

    public void init(ChefViewModel v) {
        this.viewModel = v;
        orderList.setItems(viewModel.getOrders());
        viewModel.fetchOrders();
        orderList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                Order order = viewModel.getOrder(orderList.getSelectionModel().getSelectedIndex());
                note.setText(order.getNote());
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
                    if(iq.getState() == ItemState.notStarted)
                        button.setText("Start");
                    Platform.runLater(() -> {
                        itemDetails.getChildren().add(name);
                        itemDetails.getChildren().add(description);
                        left.getChildren().add(itemDetails);
                        left.setStyle("-fx-pref-width: 280px");
                        right.getChildren().add(button);
                        right.setAlignment(Pos.BASELINE_RIGHT);
                        right.setStyle("-fx-pref-width: 80px");
                        oneItem.getChildren().add(left);
                        oneItem.getChildren().add(right);
                        vBox.getChildren().add(oneItem);
                        oneItem.setStyle("-fx-padding: 15px; -fx-border-width: 0 0 2px 0; -fx-border-color:  #ffcccc");
                    });

                }
            }
        });
    }

}
