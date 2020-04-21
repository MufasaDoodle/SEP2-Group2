package shared.networking;

import stuffs.Listing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface Sorting
{
    Listing readById(int id) throws SQLException, RemoteException;
    List<Listing> readByTitle(String title) throws SQLException, RemoteException;
    List<Listing> readByCategory(String category) throws SQLException, RemoteException;
    List<Listing> readByLocation(String location) throws SQLException, RemoteException;

    //combinations

    List<Listing> titleCategoryLocation(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> titleCategory(String title, String category) throws SQLException, RemoteException;
    List<Listing> titleLocation(String title, String location) throws SQLException, RemoteException;
    List<Listing> categoryLocation(String category, String location) throws SQLException, RemoteException;

    //sorting
    List<Listing> starRatingLowToHigh() throws SQLException, RemoteException;
    List<Listing> starRatingHighToLow() throws SQLException, RemoteException;
    List<Listing> priceLowToHigh() throws SQLException, RemoteException;
    List<Listing> priceHighToLow() throws SQLException, RemoteException;
    List<Listing> newToOld() throws SQLException, RemoteException;
    List<Listing> oldToNew() throws SQLException, RemoteException;

    //combinations&sorting
    //------title
    List<Listing> titleNewOld(String title) throws SQLException, RemoteException;
    List<Listing> titleOldNew(String title) throws SQLException, RemoteException;
    List<Listing> titleRatingLowToHigh(String title) throws SQLException, RemoteException;
    List<Listing> titleRatingHighToLow(String title) throws SQLException, RemoteException;
    List<Listing> titlePriceLowToHigh(String title) throws SQLException, RemoteException;
    List<Listing> titlePriceHighToLow(String title) throws SQLException, RemoteException;

    //---------category
    List<Listing> categoryNewOld(String category) throws SQLException, RemoteException;
    List<Listing> categoryOldNew(String category) throws SQLException, RemoteException;
    List<Listing> categoryRatingLowToHigh(String category) throws SQLException, RemoteException;
    List<Listing> categoryRatingHighToLow(String category) throws SQLException, RemoteException;
    List<Listing> categoryPriceLowToHigh(String category) throws SQLException, RemoteException;
    List<Listing> categoryPriceHighToLow(String category) throws SQLException, RemoteException;

    //------location
    List<Listing> locationNewOld(String location) throws SQLException, RemoteException;
    List<Listing> locationOldNew(String location) throws SQLException, RemoteException;
    List<Listing> locationRatingLowToHigh(String location) throws SQLException, RemoteException;
    List<Listing> locationRatingHighToLow(String location) throws SQLException, RemoteException;
    List<Listing> locationPriceLowToHigh(String location) throws SQLException, RemoteException;
    List<Listing> locationPriceHighToLow(String location) throws SQLException, RemoteException;

    //------title, category and location
    List<Listing> titleCategoryLocationNewOld(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> titleCategoryLocationOldNew(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> titleCategoryLocationRatingLowToHigh(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> titleCategoryLocationRatingHighToLow(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> titleCategoryLocationPriceLowToHigh(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> titleCategoryLocationPriceHighToLow(String title, String category, String location) throws SQLException, RemoteException;

    //----title and category
    List<Listing> titleCategoryNewOld(String title, String category) throws SQLException, RemoteException;
    List<Listing> titleCategoryOldNew(String title, String category) throws SQLException, RemoteException;
    List<Listing> titleCategoryRatingLowToHigh(String title, String category) throws SQLException, RemoteException;
    List<Listing> titleCategoryRatingHighToLow(String title, String category) throws SQLException, RemoteException;
    List<Listing> titleCategoryPriceLowToHigh(String title, String category) throws SQLException, RemoteException;
    List<Listing> titleCategoryPriceHighToLow(String title, String category) throws SQLException, RemoteException;

    //-----title and location
    List<Listing> titleLocationNewOld(String title, String location) throws SQLException, RemoteException;
    List<Listing> titleLocationOldNew(String title, String location) throws SQLException, RemoteException;
    List<Listing> titleLocationRatingLowToHigh(String title, String location) throws SQLException, RemoteException;
    List<Listing> titleLocationRatingHighToLow(String title, String location) throws SQLException, RemoteException;
    List<Listing> titleLocationPriceLowToHigh(String title, String location) throws SQLException, RemoteException;
    List<Listing> titleLocationPriceHighToLow(String title, String location) throws SQLException, RemoteException;

    //-----category and location
    List<Listing> categoryLocationNewOld(String category, String location) throws SQLException, RemoteException;
    List<Listing> categoryLocationOldNew(String category, String location) throws SQLException, RemoteException;
    List<Listing> categoryLocationRatingLowToHigh(String category, String location) throws SQLException, RemoteException;
    List<Listing> categoryLocationRatingHighToLow(String category, String location) throws SQLException, RemoteException;
    List<Listing> categoryLocationPriceLowToHigh(String category, String location) throws SQLException, RemoteException;
    List<Listing> categoryLocationPriceHighToLow(String category, String location) throws SQLException, RemoteException;
}
