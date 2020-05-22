package client.view.createlisting;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import shared.util.NumericCheck;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListingViewModel
{
  private ClientModel clientModel;

  public ListingViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
  }


  public boolean createListing(String title, String descText, String price, String category, String location, String duration, String promoted)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    int accountId = clientModel.getCurrentAccountID();

    if (!title.equals("") && !descText.equals("") && !price.equals("") && !category.equals("") && !location.equals("") && !duration.equals(""))
    {
      if (NumericCheck.isNumeric(price))
      {
        if (clientModel.createListing(title, descText, price, category, location, duration, dateFormat.format(date), accountId, promoted))
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Price must be a number!");
        alert.showAndWait();
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("All fields must be filled!");
      alert.setContentText("You left some fields empty!");
      alert.showAndWait();
    }
    return false;
  }

  public boolean accountCheck(){
    return clientModel.accountCheck();
  }
}