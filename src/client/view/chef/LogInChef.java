package client.view.chef;

import client.viewModel.Chef.ChefLogInViewModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LogInChef {
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label oops;

    private ChefLogInViewModel viewModel;

    public void init(ChefLogInViewModel viewModel)
    {
        this.viewModel = viewModel;
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
        oops.textProperty().bindBidirectional(viewModel.errorTextProperty());
        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER))
                {
                    logIn();
                }
            }
        });
    }

    public void logIn()
    {
        System.out.println("log in view");
        viewModel.logIn();
    }
}
