package client.view.createlisting;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.util.NumericCheck;

import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListingViewModel
{
  private ClientModel clientModel;
  private StringProperty error;

  public ListingViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    error = new SimpleStringProperty();
  }

  public StringProperty errorProperty()
  {
    return error;
  }


  public boolean createListing(String title, String descText, String price, String category, String location, String duration)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    if (!title.equals("") && !descText.equals("") && !price.equals("") && !category.equals("") && !location.equals("") && !duration.equals(""))
    {
      if (NumericCheck.isNumeric(price))
      {
        if (clientModel.createListing(title, descText, price, category, location, duration, dateFormat.format(date)))
        {
          error.set("Listing created");
          return true;
        }
        else
        {
          error.set("Could not contact server");
        }
      }
      else
      {
        error.set("Price must be a number");
      }
    }
    else
    {
      error.set("All fields must be filled");
    }
    return false;
  }
}
