package database.creatingListings;

import shared.networking.Sorting;
import stuffs.Listing;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ListingDAO extends Sorting
{
    Listing create(String title, String description, String category, String location, double price, String duration, Date date) throws SQLException;
    Listing createWithoutDescription(String title, String category, String location, double price, String duration, Date date) throws SQLException;


    List<Listing> getAll() throws SQLException;
    void update(Listing listing) throws SQLException;
    void delete(Listing listing) throws SQLException;

}
