package client.view.createaccount;

import client.model.AccountModel;
import client.model.MasterModelInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import shared.util.EmailCheck;

public class CreateAccountViewModel
{
  private MasterModelInterface masterModel;
  private AccountModel accountModel;
  private StringProperty error;
  private StringProperty result;

  public CreateAccountViewModel(MasterModelInterface masterModel, AccountModel accountModel)
  {
    this.masterModel = masterModel;
    this.accountModel = accountModel;
    error = new SimpleStringProperty();
    result = new SimpleStringProperty();
  }

  StringProperty errorProperty()
  {
    return error;
  }
  StringProperty resultProperty()
  {
    return result;
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

          if (accountModel.createAccount(name, email, password1, address, phoneNumber))
          {
            error.set("Account created");
            result.setValue("OK");
          }
          else
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Email is already in use!");
            alert.setContentText("Please choose another!");
            alert.showAndWait();
            result.setValue("Email used");
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warning");
          alert.setHeaderText("Passwords do not match!");
          alert.setContentText("Please check your passwords!");
          alert.showAndWait();
          result.setValue("Passwords incorrect");
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Email is not valid!");
        alert.setContentText("Please check your email again!");
        alert.showAndWait();
        result.setValue("Email incorrect");
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("All * fields must be filled!");
      alert.setContentText("Please fill all * fields!");
      alert.showAndWait();
      result.setValue("Fill in all fields");
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

          if (accountModel.createAccount(name, email, password1, address, phoneNumber, bio))
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