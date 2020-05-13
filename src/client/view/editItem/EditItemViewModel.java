package client.view.editItem;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import stuffs.Account;
import stuffs.Listing;

public class EditItemViewModel
{
  private ClientModel clientModel;
  private StringProperty title, description, category, location, duration, error, price;

  public EditItemViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    title = new SimpleStringProperty();
    description = new SimpleStringProperty();
    category = new SimpleStringProperty();
    location = new SimpleStringProperty();
    price = new SimpleStringProperty();
    duration = new SimpleStringProperty();

    error = new SimpleStringProperty();
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public StringProperty categoryProperty()
  {
    return category;
  }

  public StringProperty locationProperty()
  {
    return location;
  }

  public StringProperty priceProperty()
  {
    return price;
  }

  public StringProperty durationProperty()
  {
    return duration;
  }

  public StringProperty errorLabelProperty()
  {
    return error;
  }

  public void updateEditFields()
  {
    Listing item = clientModel.getListingByID(clientModel.getCurrentItemID());
    title.set(item.getTitle());
    description.set(item.getDescription());
    category.set(item.getDescription());
    location.set(item.getLocation());
    price.set(String.valueOf(item.getPrice()));
    duration.set(item.getDuration());
  }

  public void deleteItem(){
    clientModel.addDeletedItemId(clientModel.getCurrentItemID());
    clientModel.deleteListing(clientModel.getCurrentItemID());
  }

  public Account getAccount(int accountId){
    return clientModel.getAccountById(accountId);
  }

  public Listing getItem(){
    return clientModel.getListingByID(clientModel.getCurrentItemID());
  }

  public void updateListing(String title, String description, String category,
      String location, String duration, double price)
  {
    if (!title.equals("") && !description.equals("") && !category.equals("")
        && !String.valueOf(price).equals("") && !location.equals("")
        && !duration.equals(""))
    {
      if (clientModel
          .updateListing(title, description, category, location, price,
              duration))
      {
        error.set("Listing updated");
      }
      else
      {
        error.set("Listing could not be updated, try again later");
      }
    }
    else
    {
      error.set("All fields must be filled");
    }
  }


}
