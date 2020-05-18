package client.view.listingView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stuffs.Listing;

import java.util.ArrayList;
import java.util.List;

public class SeeListingViewModel
{
  private ClientModel clientModel;
  private StringProperty error;
  private ObservableList<Listing> listings;

  public SeeListingViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    error = new SimpleStringProperty();
  }

  public StringProperty getError()
  {
    return error;
  }

  public void listOfListings()
  {
    List<Listing> listListing = clientModel.getListings();
    List<Listing> result = new ArrayList<>();
    for (int i = 0; i < listListing.size(); i++)
    {
      if ((!clientModel.getDeletedItemIds()
          .contains(listListing.get(i).getId())))
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

  public void listOfChoice(String request, String title, String category,
      String location)
  {
    List<Listing> list = clientModel
        .getSorting(request, title, category, location);
    List<Listing> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
    {
      if ((!clientModel.getDeletedItemIds().contains(list.get(i).getId())))
      {
        result.add(list.get(i));
      }
    }
    listings = FXCollections.observableArrayList(result);
  }

  public void setItem(int itemID)
  {
    clientModel.setCurrentItemID(itemID);
  }

  public List<Integer> getDeletedItemIds()
  {
    return clientModel.getDeletedItemIds();
  }

  public void setWhereFromOpen(boolean whereFromOpen)
  {
    clientModel.setFromListingViewOpen(whereFromOpen);
  }

  public void setAccountIDToLocalID()
  {
    clientModel.setLocalAccountID();
  }
}
