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

    public void init(WaiterViewModel viewModel, ViewHandler viewHandler) {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        notifications.itemsProperty().bindBidirectional(viewModel.getNotifications());
        displayPanel.setItems(viewModel.getDetails());
        viewModel.addListeners("Notification for waiter", this::newNotification);
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

    public void newNotification(PropertyChangeEvent changeEvent){
        int newOne = (Integer)changeEvent.getNewValue();
        switch (newOne){
            case (0): {
                tableOne.setStyle(style);
                break;
            }
            case (2): {
                tableTwo.setStyle(style);
                break;
            }
            case (3): {
                tableThree.setStyle(style);
                break;
            }
            case (4): {
                tableFour.setStyle(style);
                break;
            }case (5): {
                tableFive.setStyle(style);
                break;
            }case (6): {
                tableSix.setStyle(style);
                break;
            }case (7): {
                tableSeven.setStyle(style);
                break;
            }case (8): {
                tableEight.setStyle(style);
                break;
            }case (9): {
                tableNine.setStyle(style);
                break;
            }
            case (10): {
                tableTen.setStyle(style);
                break;
            }
            case (11): {
                tableEleven.setStyle(style);
                break;
            }case (12): {
                tableTwelve.setStyle(style);
                break;
            }case (13): {
                tableThirteen.setStyle(style);
                break;
            }case (14): {
                tableFourteen.setStyle(style);
                break;
            }case (15): {
                tableFifteen.setStyle(style);
                break;
            }
            case (16): {
                tableSixteen.setStyle(style);
                break;
            }
        }

    }

    public void one(ActionEvent actionEvent) {
        viewModel.refreshDetails("table1");
        if(viewModel.readyToClose("table1")) closeTable.setDisable(false);
    }
    public void two(ActionEvent actionEvent) {
        viewModel.refreshDetails("table2");
        if(viewModel.readyToClose("table1")) closeTable.setDisable(false);
    }
    public void three(ActionEvent actionEvent) {
        viewModel.refreshDetails("table3");
    }
    public void four(ActionEvent actionEvent) {
        viewModel.refreshDetails("table4");
    }
    public void five(ActionEvent actionEvent) {
        viewModel.refreshDetails("table5");
    }
    public void six(ActionEvent actionEvent) {
        viewModel.refreshDetails("table6");
    }
    public void seven(ActionEvent actionEvent) {
        viewModel.refreshDetails("table7");
    }
    public void eight(ActionEvent actionEvent) {
        viewModel.refreshDetails("table8");
    }
    public void nine(ActionEvent actionEvent) {
        viewModel.refreshDetails("table9");
    }
    public void ten(ActionEvent actionEvent) {
        viewModel.refreshDetails("table10");
    }
    public void eleven(ActionEvent actionEvent) {
        viewModel.refreshDetails("table11");
    }
    public void twelve(ActionEvent actionEvent) {
        viewModel.refreshDetails("table12");
    }
    public void thirteen(ActionEvent actionEvent) {
        viewModel.refreshDetails("table13");
    }
    public void fourteen(ActionEvent actionEvent) {
        viewModel.refreshDetails("table14");
    }
    public void fifteen(ActionEvent actionEvent) {
        viewModel.refreshDetails("table15");
    }
    public void sixteen(ActionEvent actionEvent){
        viewModel.refreshDetails("table16");
    }


   /* public void four(ActionEvent actionEvent) {
        tableFour.setStyle(normal);
    }
    public void five(ActionEvent actionEvent) {
        tableFive.setStyle(normal);
    }
    public void six(ActionEvent actionEvent) {
        tableSix.setStyle(normal);
    }
    public void seven(ActionEvent actionEvent) {
        tableSeven.setStyle(normal);
    }
    public void eight(ActionEvent actionEvent) {
        tableEight.setStyle(normal);
    }
    public void nine(ActionEvent actionEvent) {
        tableNine.setStyle(normal);
    }
    public void ten(ActionEvent actionEvent) {
        tableTen.setStyle(normal);
    }
    public void eleven(ActionEvent actionEvent) {
        tableEleven.setStyle(normal);
    }
    public void twelve(ActionEvent actionEvent) {
        tableTwelve.setStyle(normal);
    }
    public void thirteen(ActionEvent actionEvent) {
        tableThirteen.setStyle(normal);
    }
    public void fourteen(ActionEvent actionEvent) {
        tableFourteen.setStyle(normal);
    }
    public void fifteen(ActionEvent actionEvent) {
        tableFifteen.setStyle(normal);
    }
    public void sixteen(ActionEvent actionEvent){
        tableSixteen.setStyle(normal);
    }*/

    public void closeOrder(ActionEvent actionEvent) {
        //viewModel.requestClose();
    }
}
