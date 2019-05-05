package client.view.chef;

import BasicClasses.Order;
import client.viewModel.Chef.ChefViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Chef {
    private ChefViewModel viewModel;
    private ObservableList<Order> orders = FXCollections.observableArrayList();

    @FXML
    private ListView orderList;

    @FXML
    private TextArea note;

    @FXML
    private ScrollPane orderItems;

    @FXML
    private void sendPartial(){

    }

    @FXML
    private void closeOrder(){

    }

    private void init(ChefViewModel vm){
        this.viewModel = vm;
        viewModel.addListener("NewOrderChef", this::setItems);
    }

    public void setItems(PropertyChangeEvent support){
        orders = (ObservableList) support.getNewValue();
        orderList.setItems(orders);
    }

//    private void setItems(PropertyChangeEvent propertyChangeEvent) {
//        ArrayList<Order> itemsFromOrder = (ArrayList<Order>) propertyChangeEvent.getNewValue();
//        orders = (ObservableList)itemsFromOrder;
//        for (int i = 0; i < orders.size(); ++i) {
//            HBox box = new HBox();
//            box.setId(orders.get(i).getTableId());
//
//
//            Label name = new Label(orders.get(i).getItems().get(i).getName());
//            name.setStyle("-fx-font-size: 27;");
//            name.setStyle("-fx-alignment: center;");
//            name.setAlignment(Pos.BASELINE_CENTER);
//
//            RadioButton started = new RadioButton();
//            RadioButton done = new RadioButton();
//
//            HBox checkBox = new HBox();
//            checkBox.getChildren().add(started);
//            checkBox.getChildren().add(done);
//            checkBox.setAlignment(Pos.BASELINE_RIGHT);
//
//
//            Platform.runLater(() -> {
//                box.getChildren().add(name);
//                box.getChildren().add(checkBox);
//               // boxes.add(box);
//                // !!!!!!!!!!!!!!!!orderItems.getChildren()add(box);
//            });
//        }

 //   }
}
