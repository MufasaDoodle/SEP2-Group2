package client.model;

import client.networking.Client;
import javafx.scene.control.Alert;
import stuffs.Account;
import stuffs.FeedbackToItem;
import stuffs.Listing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListingsModelManager implements ListingsModel
{
  private DataModel dataModel;
  private MasterModelInterface master;

  public ListingsModelManager(DataModel dataModel, MasterModelInterface master)
  {
    this.dataModel = dataModel;
    this.master = master;
  }

  @Override public List<Listing> getListings()
  {
    System.out.println("All listings have been retrieved");
    return dataModel.getClient().getListings();
  }

  @Override public List<Listing> getSorting(String request, String title, String category, String location)
  {
    System.out.println("Listings have been retrieved");
    return dataModel.getClient().getSorting(request, title, category, location);
  }

  @Override public boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId, String promoted)
  {
    System.out.println("Listing created!");
    accountId = master.getCurrentAccountID();
    return dataModel.getClient().createListing(title, descText, price, category, location, duration, date, accountId, promoted);
  }

  @Override public String getItemName()
  {
    return dataModel.getItemName();
  }

  @Override public boolean updateListing(String title, String description, String category, String location, double price, String duration, String rented, String promoted)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    Listing currentListing = dataModel.getClient().getListingByID(dataModel.getCurrentItemID());
    Listing updatedListing = new Listing(title, description, category, location, price, duration, dateFormat.format(date), currentListing.getId(), currentListing.getAccountId(), rented, promoted);
    if (dataModel.getClient().updateListing(updatedListing))
    {
      System.out.println("Listing updated");
      return true;
    }
    return false;
  }

  @Override public boolean updateListingRented(String title, String description, String category, String location, double price, String duration, String rented, int itemId, int accountId, String promoted)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    Listing updatedListing = new Listing(title, description, category, location, price, duration, dateFormat.format(date), itemId, accountId, rented, promoted);
    if (dataModel.getClient().updateListing(updatedListing))
    {
      System.out.println("Listing updated");
      return true;
    }
    return false;
  }

  @Override public void deleteListing(int id)
  {
    System.out.println("Listing deleted");
    dataModel.getClient().deleteListing(id);
  }

  @Override public void addDeletedItemId(int itemId)
  {
    dataModel.getClient().addDeletedItemId(itemId);
  }

  @Override public List<Integer> getDeletedItemIds()
  {
    return dataModel.getClient().getDeletedItemIds();
  }

  @Override public void setFromListingViewOpen(boolean whereFrom)
  {
    dataModel.setFromListingViewOpen(whereFrom);
  }

  @Override public boolean getFromListingViewOpen()
  {
    return dataModel.getFromListingViewOpen();
  }

  @Override public void deleteItemByAccount(int id)
  {
    System.out.println("Item deleted");
    dataModel.getClient().deleteItemByAccount(id);
  }

  //master

}
