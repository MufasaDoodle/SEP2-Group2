package client.view.editItem;

import client.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import shared.transferobjects.Account;
import shared.transferobjects.Listing;

public class EditItemViewModel
{
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private FeedbackModel feedbackModel;
  private ModeratorModel moderatorModel;
  private TransactionModel transactionModel;
  private StringProperty title, description, category, location, duration, price;
  private boolean itemAvailability;
  private boolean promoted;

  public EditItemViewModel(MasterModelInterface masterModel, ListingsModel listingsModel, FeedbackModel feedbackModel, ModeratorModel moderatorModel, TransactionModel transactionModel)
  {
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.feedbackModel = feedbackModel;
    this.moderatorModel = moderatorModel;
    this.transactionModel = transactionModel;
    title = new SimpleStringProperty();
    description = new SimpleStringProperty();
    category = new SimpleStringProperty();
    location = new SimpleStringProperty();
    price = new SimpleStringProperty();
    duration = new SimpleStringProperty();
  }

  public boolean getItemAv()
  {
    return itemAvailability;
  }

  public void setItemAvailability(boolean itemAvailability)
  {
    this.itemAvailability = itemAvailability;
  }

  public boolean getPromoted()
  {
    return promoted;
  }

  public void setPromoted(boolean promoted)
  {
    this.promoted = promoted;
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

  public void updateEditFields()
  {
    Listing item = masterModel.getListingByID(masterModel.getCurrentItemID());
    title.set(item.getTitle());
    description.set(item.getDescription());
    category.set(item.getDescription());
    location.set(item.getLocation());
    price.set(String.valueOf(item.getPrice()));
    duration.set(item.getDuration());
    if (item.getRented().equals("Available"))
    {
      setItemAvailability(true);
    }
    else
    {
      setItemAvailability(false);
    }
    if (item.getPromoted().equals("*"))
    {
      setPromoted(true);
    }
    else
    {
      setPromoted(false);
    }
  }

  public void deleteItem()
  {
    listingsModel.addDeletedItemId(masterModel.getCurrentItemID());
    listingsModel.deleteListing(masterModel.getCurrentItemID());
  }

  public Account getAccount(int accountId)
  {
    return masterModel.getAccountById(accountId);
  }

  public Listing getItem()
  {
    return masterModel.getListingByID(masterModel.getCurrentItemID());
  }

  public void updateListing(String title, String description, String category, String location, String duration, double price, String rented, String promoted)
  {
    if (!title.equals("") && !description.equals("") && !category.equals("") && !String.valueOf(price).equals("") && !location.equals("") && !duration.equals(""))
    {
      if (listingsModel.updateListing(title, description, category, location, price, duration, rented, promoted))
      {
        Alert promote = new Alert(Alert.AlertType.INFORMATION);
        promote.setTitle("Item Update");
        promote.setHeaderText("Successfully updated item!");
        promote.showAndWait();
      }
      else
      {
        Alert promote = new Alert(Alert.AlertType.WARNING);
        promote.setTitle("Item Update");
        promote.setHeaderText("Listing could not be updated, try again later!");
        promote.showAndWait();
      }
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Item Update");
      promote.setHeaderText("All fields must be filled!");
      promote.showAndWait();
    }
  }

  public void deleteItemRequest()
  {
    transactionModel.deleteRequest(masterModel.getCurrentItemID());
  }

  public void deleteItemTransaction()
  {
    transactionModel.deleteTransactionByItem(masterModel.getCurrentItemID());
  }

  public void deleteItemFeedback()
  {
    feedbackModel.deleteFeedbackByItemId(masterModel.getCurrentItemID());
  }

  public void deleteItemReport()
  {
    moderatorModel.deleteReportByItem(masterModel.getCurrentItemID());
  }

  public void addDeletedItemId()
  {
    listingsModel.addDeletedItemId(masterModel.getCurrentItemID());
  }

  public boolean accountCheck()
  {
    return masterModel.accountCheck();
  }

}