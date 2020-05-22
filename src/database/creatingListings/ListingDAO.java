package database.creatingListings;

import shared.networking.Sorting;
import stuffs.Listing;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ListingDAO extends Sorting
{
  Listing create(String title, String description, String category,
      String location, double price, String duration, Date date, int accountId, String promoted)
      throws SQLException;
  /*Listing createWithoutDescription(String title, String category,
      String location, double price, String duration, Date date, int accountId)
      throws SQLException;*/
  /*Listing createRentedListing(String title, String description, String category,
      String location, double price, String duration, Date date, int accountId)
      throws SQLException;*/

  List<Listing> getAll() throws SQLException;
  void update(Listing listing) throws SQLException;
  void delete(int id) throws SQLException;
  void deleteByAccount(int id) throws SQLException;

  List<Listing> readByAccountId(int accountId) throws SQLException;

}