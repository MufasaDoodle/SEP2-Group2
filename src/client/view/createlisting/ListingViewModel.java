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
      clientModel.createListing(title, descText, price, category, location, duration, date);
      error.set("Listing created!");
    }
    else
    {
      error.set("All fields must be filled");
    }
  }
}
