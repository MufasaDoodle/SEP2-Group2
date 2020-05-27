package client.model;

import shared.transferobjects.Listing;

import java.util.List;

public interface ListingsModel
{
  List<Listing> getListings();
  List<Listing> getSorting(String request, String title, String category, String location);
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId, String promoted);
  String getItemName();
  boolean updateListing(String title, String description, String category, String location, double price, String duration, String rented, String promoted);
  boolean updateListingRented(String title, String description, String category, String location, double price, String duration, String rented, int itemId, int accountId, String promoted);
  void deleteListing(int id);
  void addDeletedItemId(int itemId);
  List<Integer> getDeletedItemIds();
  void setFromListingViewOpen(boolean whereFrom);
  boolean getFromListingViewOpen();
  void deleteItemByAccount(int id);
}
