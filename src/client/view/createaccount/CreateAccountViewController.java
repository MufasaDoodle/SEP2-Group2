package client.view.createaccount;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class CreateAccountViewController implements ViewController
{
  @FXML private TextField nameField;
  @FXML private TextField emailField;
  @FXML private PasswordField password1Field;
  @FXML private PasswordField password2Field;
  @FXML private TextField addressField;
  @FXML private TextField phoneNumberField;
  @FXML private TextArea bioField;

  @FXML private Label AccountErrorLabel;

  private CreateAccountViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getCreateAccountViewModel();
    AccountErrorLabel.textProperty().bind(viewModel.errorProperty());
  }

  public void OnAccountCreate()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Create Account");
    alert.setHeaderText("Do you want to create an account?");

    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK)
    {
      if (bioField.getText().equals(""))
      {
        viewModel.createAccount(nameField.getText(), emailField.getText(),
            password1Field.getText(), password2Field.getText(),
            addressField.getText(), phoneNumberField.getText());
      }
      else
      {
        viewModel.createAccount(nameField.getText(), emailField.getText(),
            password1Field.getText(), password2Field.getText(),
            addressField.getText(), phoneNumberField.getText(),
            bioField.getText());
      }
    }
  }

  public void OnLogInView()
  {
    vh.openLogInScene();
    nameField.setText("");
    emailField.setText("");
    password1Field.setText("");
    password2Field.setText("");
    addressField.setText("");
    phoneNumberField.setText("");
    bioField.setText("");
    viewModel.errorProperty().set("");
  }
}