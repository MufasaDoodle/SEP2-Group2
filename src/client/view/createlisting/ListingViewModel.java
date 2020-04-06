package client.view.createlisting;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

  public void createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    if (!title.equals("") && !descText.equals("") && !price.equals("") && !category.equals("") && !location.equals("") && !duration.equals("") && !date.equals(""))
    {
      error.set("Listing created!");
      clientModel.createListing(title, descText, price, category, location, duration, date);
    }
    else
    {
      error.set("All fields must be filled");
    }
  }
}
