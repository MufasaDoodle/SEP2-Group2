package shared.networking;

import com.sun.prism.sw.SWMaskTexture;
import stuffs.Listing;

import javax.print.DocFlavor;
import java.lang.module.ResolvedModule;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface Sorting
{
    Listing readById(int id) throws SQLException, RemoteException;
    List<Listing> readByTitle(String title) throws SQLException, RemoteException;
    List<Listing> availableTitle(String title) throws SQLException;
    List<Listing> readByCategory(String category) throws SQLException, RemoteException;
    List<Listing> availableCategory(String category) throws SQLException, RemoteException;
    List<Listing> readByLocation(String location) throws SQLException, RemoteException;
    List<Listing> availableLocation(String location) throws SQLException, RemoteException;
    List<Listing> isAvailable() throws SQLException, RemoteException;

    //combinations

    List<Listing> titleCategoryLocation(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> titleCategory(String title, String category) throws SQLException, RemoteException;
    List<Listing> titleLocation(String title, String location) throws SQLException, RemoteException;
    List<Listing> categoryLocation(String category, String location) throws SQLException, RemoteException;
    List<Listing> availableTitleCategoryLocation(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> availableTitleCategory(String title, String category) throws SQLException, RemoteException;
    List<Listing> availableTitleLocation(String title, String location) throws SQLException, RemoteException;
    List<Listing> availableCategoryLocation(String category, String location) throws SQLException, RemoteException;


    //sorting
    List<Listing> priceLowToHigh() throws SQLException, RemoteException;
    List<Listing> priceHighToLow() throws SQLException, RemoteException;
    List<Listing> availablePriceHighToLow() throws SQLException, RemoteException;
    List<Listing> availablePriceLowToHigh() throws SQLException, RemoteException;

    //combinations&sorting
    //------title
    List<Listing> titlePriceLowToHigh(String title) throws SQLException, RemoteException;
    List<Listing> titlePriceHighToLow(String title) throws SQLException, RemoteException;
    List<Listing> availableTitlePriceLowToHigh(String title) throws SQLException, RemoteException;
    List<Listing> availableTitlePriceHighToLow(String title) throws SQLException, RemoteException;

    //---------category
    List<Listing> categoryPriceLowToHigh(String category) throws SQLException, RemoteException;
    List<Listing> categoryPriceHighToLow(String category) throws SQLException, RemoteException;
    List<Listing> availableCategoryPriceLowToHigh(String category) throws SQLException, RemoteException;
    List<Listing> availableCategoryPriceHighToLow(String category) throws SQLException, RemoteException;

    //------location
    List<Listing> locationPriceLowToHigh(String location) throws SQLException, RemoteException;
    List<Listing> locationPriceHighToLow(String location) throws SQLException, RemoteException;
    List<Listing> availableLocationPriceLowToHigh(String location) throws SQLException, RemoteException;
    List<Listing> availableLocationPriceHighToLow(String location) throws SQLException, RemoteException;

    //------title, category and location
    List<Listing> titleCategoryLocationPriceLowToHigh(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> titleCategoryLocationPriceHighToLow(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> availableTitleCategoryLocationPriceLowToHigh(String title, String category, String location) throws SQLException, RemoteException;
    List<Listing> availableTitleCategoryLocationPriceHighToLow(String title, String category, String location) throws SQLException, RemoteException;

    //----title and category
    List<Listing> titleCategoryPriceLowToHigh(String title, String category) throws SQLException, RemoteException;
    List<Listing> titleCategoryPriceHighToLow(String title, String category) throws SQLException, RemoteException;
    List<Listing> availableTitleCategoryPriceLowToHigh(String title, String category) throws SQLException, RemoteException;
    List<Listing> availableTitleCategoryPriceHighToLow(String title, String category) throws SQLException, RemoteException;

    //-----title and location
    List<Listing> titleLocationPriceLowToHigh(String title, String location) throws SQLException, RemoteException;
    List<Listing> titleLocationPriceHighToLow(String title, String location) throws SQLException, RemoteException;
    List<Listing> availableTitleLocationPriceLowToHigh(String title, String location) throws SQLException, RemoteException;
    List<Listing> availableTitleLocationPriceHighToLow(String title, String location) throws SQLException, RemoteException;

    //-----category and location
    List<Listing> categoryLocationPriceLowToHigh(String category, String location) throws SQLException, RemoteException;
    List<Listing> categoryLocationPriceHighToLow(String category, String location) throws SQLException, RemoteException;
    List<Listing> availableCategoryLocationPriceLowToHigh(String category, String location) throws SQLException, RemoteException;
    List<Listing> availableCategoryLocationPriceHighToLow(String category, String location) throws SQLException, RemoteException;

}
