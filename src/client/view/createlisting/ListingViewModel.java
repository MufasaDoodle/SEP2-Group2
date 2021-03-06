package client.view.createlisting;

import client.model.ListingsModel;
import client.model.MasterModelInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import shared.util.NumericCheck;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListingViewModel
{
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private StringProperty result;

  public ListingViewModel(MasterModelInterface masterModel, ListingsModel listingsModel)
  {
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    result = new SimpleStringProperty();
  }

  public StringProperty getResultProperty()
  {
    return result;
  }

  public boolean createListing(String title, String descText, String price, String category, String location, String duration, String promoted)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    int accountId = masterModel.getCurrentAccountID();

    if (!title.equals("") && !descText.equals("") && !price.equals("") && !category.equals("") && !location.equals("") && !duration.equals(""))
    {
      if (NumericCheck.isNumeric(price))
      {
        if (listingsModel.createListing(title, descText, price, category, location, duration, dateFormat.format(date), accountId, promoted))
        {
          result.setValue("OK");
          return true;
        }
        else
        {
          result.setValue("NOT OK");
          return false;
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Price must be a number!");
        alert.showAndWait();
        result.setValue("Price wrong");
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("All fields must be filled!");
      alert.setContentText("You left some fields empty!");
      alert.showAndWait();
      result.setValue("empty fields");
    }
    return false;
  }

  public boolean accountCheck(){
    return masterModel.accountCheck();
  }
}