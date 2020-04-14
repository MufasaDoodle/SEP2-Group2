package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements ViewController
{

  @FXML private Label errorLabel;
  @FXML private TextField emailField;
  @FXML private TextField passwordField;
  private LoginViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getLoginViewModel();
    errorLabel.textProperty().bind(viewModel.errorProperty());
  }

  public void onLogIn(ActionEvent actionEvent)
  {
    if (viewModel.checkLogIn(emailField.getText(), passwordField.getText()))
    {
      vh.openListingScene();
    }
  }

  public void onCreateAccountView(ActionEvent actionEvent)
  {
    vh.openAccountCreateScene();
  }
}
