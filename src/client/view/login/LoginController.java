package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements ViewController
{

  @FXML private Label errorLabel;
  @FXML private TextField emailField;
  @FXML private PasswordField passwordField;
  private LoginViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getLoginViewModel();
    errorLabel.textProperty().bind(viewModel.errorProperty());
  }

  public void onLogIn()
  {
    if (viewModel.checkLogIn(emailField.getText(), passwordField.getText()))
    {
      viewModel.setAccountId(emailField.getText());
      viewModel.setAccountName(emailField.getText());
      vh.openSeeListingScene();
      emailField.setText("");
      passwordField.setText("");
    }

  }

  public void onCreateAccountView()
  {
    vh.openAccountCreateScene();
  }

  public void onChatView()
  {
    vh.openChatScene();
  }
}
