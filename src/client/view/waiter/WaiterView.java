package client.view.waiter;

import client.view.ViewHandler;
import client.viewModel.waiter.WaiterViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;


public class WaiterView {
    private WaiterViewModel viewModel;
    private ViewHandler viewHandler;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String style = "-fx-background-color: rgb(112, 219, 112);";
    private String normal = "-fx-background-color: rgb(255, 230, 230);";
    private int lastSelectedTable;

    public void init(WaiterViewModel viewModel, ViewHandler viewHandler) {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        lastSelectedTable = 0;
        notifications.itemsProperty().bindBidirectional(viewModel.getNotifications());
        displayPanel.setItems(viewModel.getDetails());
        closeTable.setDisable(true);

    }

    @FXML
    private Button tableOne, tableTwo, tableThree, tableFour, tableFive,
            tableSix, tableSeven, tableEight, tableNine, tableTen, tableEleven,
            tableTwelve, tableThirteen, tableFourteen, tableFifteen, tableSixteen;

    @FXML
    private ListView notifications;

    @FXML
    private Label updateLabel;

    @FXML
    public Button closeTable;

    @FXML
    private ListView displayPanel;

    @FXML
    public void notificationSelected(){
        displayPanel.setItems(null);
        displayPanel.setItems(viewModel.getDetails());
    }

    //NOT WORKING
    @FXML
    public void takeOver(){
        notifications.getFocusModel().getFocusedItem().getClass().getName().toUpperCase();
        //notifications.placeholderProperty().setValue("Taken");
    }


    public void one(ActionEvent actionEvent) {
        viewModel.refreshDetails("table1");
        lastSelectedTable = 1;
        if(viewModel.readyToClose("table1")) closeTable.setDisable(false);
    }
    public void two(ActionEvent actionEvent) {
        viewModel.refreshDetails("table2");
        lastSelectedTable = 2;
        if(viewModel.readyToClose("table1")) closeTable.setDisable(false);
    }
    public void three(ActionEvent actionEvent) {
        viewModel.refreshDetails("table3");
        lastSelectedTable = 3;
    }
    public void four(ActionEvent actionEvent) {
        viewModel.refreshDetails("table4");
        lastSelectedTable = 4;
    }
    public void five(ActionEvent actionEvent) {
        viewModel.refreshDetails("table5");
        lastSelectedTable = 5;
    }
    public void six(ActionEvent actionEvent) {
        viewModel.refreshDetails("table6");
        lastSelectedTable = 6;
    }
    public void seven(ActionEvent actionEvent) {
        viewModel.refreshDetails("table7");
        lastSelectedTable = 7;
    }
    public void eight(ActionEvent actionEvent) {
        viewModel.refreshDetails("table8");
        lastSelectedTable = 8;
    }
    public void nine(ActionEvent actionEvent) {
        viewModel.refreshDetails("table9");
        lastSelectedTable = 9;
    }
    public void ten(ActionEvent actionEvent) {
        viewModel.refreshDetails("table10");
        lastSelectedTable = 10;
    }
    public void eleven(ActionEvent actionEvent) {
        viewModel.refreshDetails("table11");
        lastSelectedTable = 11;
    }
    public void twelve(ActionEvent actionEvent) {
        viewModel.refreshDetails("table12");
        lastSelectedTable = 12;
    }
    public void thirteen(ActionEvent actionEvent) {
        viewModel.refreshDetails("table13");
        lastSelectedTable = 13;
    }
    public void fourteen(ActionEvent actionEvent) {
        viewModel.refreshDetails("table14");
        lastSelectedTable = 14;
    }
    public void fifteen(ActionEvent actionEvent) {
        viewModel.refreshDetails("table15");
        lastSelectedTable = 15;
    }
    public void sixteen(ActionEvent actionEvent){
        viewModel.refreshDetails("table16");
        lastSelectedTable = 16;
    }

    public void closeOrder(ActionEvent actionEvent) {
        viewModel.requestClose(lastSelectedTable);
    }
}
