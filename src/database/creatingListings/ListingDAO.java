package database.creatingListings;

import stuffs.Listing;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ListingDAO
{
    Listing create(String title, String description, String category, String location, double price, String duration, Date date) throws SQLException;
    Listing createWithoutDescription(String title, String category, String location, double price, String duration, Date date) throws SQLException;
    Listing readById(int id) throws SQLException;
    List<Listing> readByTitle(String title) throws SQLException;
    List<Listing> readByCategory(String category) throws SQLException;
    List<Listing> readByLocation(String location) throws SQLException;

    //combinations

    List<Listing> titleCategoryLocation(String title, String category, String location) throws SQLException;
    List<Listing> titleCategory(String title, String category) throws SQLException;
    List<Listing> titleLocation(String title, String location) throws SQLException;
    List<Listing> categoryLocation(String category, String location) throws SQLException;

    //sorting
    List<Listing> starRatingLowToHigh() throws SQLException;
    List<Listing> starRatingHighToLow() throws SQLException;
    List<Listing> priceLowToHigh() throws SQLException;
    List<Listing> priceHighToLow() throws SQLException;

    void update(Listing listing) throws SQLException;
    void delete(Listing listing) throws SQLException;

}
