package client.view.createaccount;

import client.model.ClientModel;
import client.model.ListingsModel;
import client.model.MasterModelInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import shared.util.EmailCheck;

public class CreateAccountViewModel
{
  private ClientModel clientModel;
  private MasterModelInterface masterModel;
  private StringProperty error;

  public CreateAccountViewModel(ClientModel clientModel, MasterModelInterface masterModel)
  {
    this.clientModel = clientModel;
    this.masterModel = masterModel;
    error = new SimpleStringProperty();
  }

  StringProperty errorProperty()
  {
    return error;
  }

  public void createAccount(String name, String email, String password1, String password2, String address, String phoneNumber)
  {
    if (!name.equals("") && !email.equals("") && !password1.equals("") && !password2.equals("") && !address.equals("") && !phoneNumber.equals(""))
    {
      if (EmailCheck.isValid(email))
      {
        if (password1.equals(password2))
        {
          //createAccount returns a confirmation message, and this was my way of setting it. Most definitely not how it should be done
          /*error.set(chatSystem.createAccount(username, password));
          System.out.println(chatSystem.getAllAccounts());*/

          if (clientModel.createAccount(name, email, password1, address, phoneNumber))
          {
            error.set("Account created");
          }
          else
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Email is already in use!");
            alert.setContentText("Please choose another!");
            alert.showAndWait();
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warning");
          alert.setHeaderText("Passwords do not match!");
          alert.setContentText("Please check your passwords!");
          alert.showAndWait();
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Email is not valid!");
        alert.setContentText("Please check your email again!");
        alert.showAndWait();
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("All * fields must be filled!");
      alert.setContentText("Please fill all * fields!");
      alert.showAndWait();
    }
  }

  public void createAccount(String name, String email, String password1, String password2, String address, String phoneNumber, String bio)
  {
    if (!name.equals("") && !email.equals("") && !password1.equals("") && !password2.equals("") && !address.equals("") && !phoneNumber.equals("") && !bio.equals(""))
    {
      if (EmailCheck.isValid(email))
      {
        if (password1.equals(password2))
        {
          //createAccount returns a confirmation message, and this was my way of setting it. Most definitely not how it should be done
          /*error.set(chatSystem.createAccount(username, password));
          System.out.println(chatSystem.getAllAccounts());*/

          if (clientModel.createAccount(name, email, password1, address, phoneNumber, bio))
          {
            error.set("Account created");

          }
          else
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Email is already in use!");
            alert.setContentText("Please choose another!");
            alert.showAndWait();
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warning");
          alert.setHeaderText("Passwords do not match!");
          alert.setContentText("Please check your passwords!");
          alert.showAndWait();
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Email is not valid!");
        alert.setContentText("Please check your email again!");
        alert.showAndWait();
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("All * fields must be filled!");
      alert.setContentText("Please fill all * fields!");
      alert.showAndWait();
    }
  }
}