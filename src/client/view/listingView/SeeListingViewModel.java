package client.view.listingView;

import client.model.ChatModel;
import client.model.ListingsModel;
import client.model.MasterModelInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import stuffs.Account;
import stuffs.Listing;

import java.util.ArrayList;
import java.util.List;

public class SeeListingViewModel
{
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private ChatModel chatModel;
  private StringProperty error;
  private ObservableList<Listing> listings;

  public SeeListingViewModel(MasterModelInterface masterModel, ListingsModel listingsModel, ChatModel chatModel)
  {
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.chatModel = chatModel;
    error = new SimpleStringProperty();
  }

  public StringProperty getError()
  {
    return error;
  }

  public void listOfListings()
  {
    List<Listing> listListing = listingsModel.getListings();
    List<Listing> result = new ArrayList<>();
    for (int i = 0; i < listListing.size(); i++)
    {
      if ((!listingsModel.getDeletedItemIds().contains(listListing.get(i).getId())))
      {
        result.add(listListing.get(i));
      }
    }
    listings = FXCollections.observableArrayList(result);
  }

  ObservableList<Listing> getListings()
  {
    return listings;
  }

  public void listOfChoice(String request, String title, String category, String location)
  {
    List<Listing> list = listingsModel.getSorting(request, title, category, location);
    List<Listing> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
    {
      if ((!listingsModel.getDeletedItemIds().contains(list.get(i).getId())))
      {
        result.add(list.get(i));
      }
    }
    listings = FXCollections.observableArrayList(result);
  }

  public void setItem(int itemID)
  {
    masterModel.setCurrentItemID(itemID);
  }

  public List<Integer> getDeletedItemIds()
  {
    return listingsModel.getDeletedItemIds();
  }

  public void setWhereFromOpen(boolean whereFromOpen)
  {
    listingsModel.setFromListingViewOpen(whereFromOpen);
  }

  public void setAccountIDToLocalID()
  {
    chatModel.setLocalAccountID();
  }

  public boolean accountCheck()
  {
    return masterModel.accountCheck();
  }

  public Account checkBannedAccount(int itemId)
  {
    if (masterModel.getListingByID(itemId) == null)
    {
      masterModel.setCurrentItemID(1);
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
      alert.showAndWait();
    }
    else
    {
      int accountId = masterModel.getListingByID(itemId).getAccountId();
      return masterModel.getAccountById(accountId);
    }
    return null;
  }

}