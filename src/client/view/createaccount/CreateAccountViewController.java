package client.view.createaccount;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateAccountViewController implements ViewController
{
  @FXML private TextField nameField;
  @FXML private TextField emailField;
  @FXML private PasswordField password1Field;
  @FXML private PasswordField password2Field;
  @FXML private TextField addressField;
  @FXML private TextField phoneNumberField;
  @FXML private TextField pictureField;
  @FXML private TextField bioField;

  @FXML private Label AccountErrorLabel;

  private CreateAccountViewModel viewModel;
  private ViewHandler vh;


  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getCreateAccountViewModel();
    AccountErrorLabel.textProperty().bind(viewModel.errorProperty());
  }

  public void OnAccountCreate(ActionEvent actionEvent)
  {
    viewModel.createAccount(nameField.getText(), emailField.getText(),password1Field.getText(), password2Field.getText(),addressField.getText(), phoneNumberField.getText());
  }

  public void OnLogInView(ActionEvent actionEvent)
  {
    vh.openLogInScene();
    nameField.setText("");
    emailField.setText("");
    password1Field.setText("");
    password2Field.setText("");
    addressField.setText("");
    phoneNumberField.setText("");
    pictureField.setText("");
    bioField.setText("");
    viewModel.errorProperty().set("");
  }
}
