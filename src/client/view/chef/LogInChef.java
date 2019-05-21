package client.view.chef;

import client.viewModel.Chef.ChefLogInViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

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
    }

    public void logIn()
    {
        viewModel.logIn();
    }
}
