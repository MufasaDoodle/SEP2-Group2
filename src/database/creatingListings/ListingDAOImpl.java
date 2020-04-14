package database.creatingListings;

import stuffs.Listing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListingDAOImpl implements ListingDAO
{

    private static ListingDAOImpl instance;

    private ListingDAOImpl() throws SQLException
    {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized ListingDAOImpl getInstance() throws SQLException
    {
        if (instance == null) {
            instance = new ListingDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=SEP2", "group2", "password");
    }

    @Override
    public Listing create(String title, String description, String category, String location, double price, String duration, String date) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Listings(title, description, category, location, price, duration, date) VALUES(?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, category);
            statement.setString(4, location);
            statement.setString(5, String.valueOf(price));
            statement.setString(6, duration);
            statement.setString(7, date);
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next())
                return new Listing(title, description, category, location, price, duration, date, keys.getInt(8));
            else
                throw new SQLException("No keys generated");
        }
    }

    @Override
    public Listing createWithoutDescription(String title, String category, String location, double price, String duration, String date) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Listings(title, category, location, price, duration, date) VALUES(?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.setString(3, category);
            statement.setString(4, location);
            statement.setString(5, String.valueOf(price));
            statement.setString(6, duration);
            statement.setString(7, date);
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next())
                return new Listing(title, category, location, price, duration, date, keys.getInt(1));
            else
                throw new SQLException("No keys generated");
        }
    }

    @Override
    public Listing readById(int id) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                return new Listing(title, description, category, location, price, duration, date, id);
            }
            else
            {
                return null;
            }
        }
    }

    @Override
    public List<Listing> readByTitle(String title) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings WHERE title LIKE ?");
            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String category = resultSet.getString("category");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> readByCategory(String category) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings WHERE category LIKE ?");
            statement.setString(1, "%" + category + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> readByLocation(String location) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings WHERE location LIKE ?");
            statement.setString(1, "%" + location + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String category = resultSet.getString("category");
                String title = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> titleCategoryLocation(String title, String category, String location) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ?" );
            statement.setString(1, "%" + title + "%");
            statement.setString(2, "%" + category + "%");
            statement.setString(3, "%" + location + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> titleCategory(String title, String category) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings WHERE title LIKE ? AND category LIKE ?");
            statement.setString(1, "%" + title + "%");
            statement.setString(1, "%" + category + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> titleLocation(String title, String location) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings WHERE title LIKE ? AND location LIKE ?" );
            statement.setString(1, "%" + title + "%");
            statement.setString(2, "%" + location + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String category = resultSet.getString("category");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> categoryLocation(String category, String location) throws SQLException {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings WHERE category LIKE ? AND location LIKE ?" );
            statement.setString(1, "%" + category + "%");
            statement.setString(2, "%" + location + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> starRatingLowToHigh() throws SQLException{
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings GROUP BY avgStarRating ORDER BY ASC" );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> starRatingHighToLow() throws SQLException{
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings GROUP BY avgStarRating ORDER BY DESC" );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> priceLowToHigh() throws SQLException{
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings GROUP BY price ORDER BY ASC" );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public List<Listing> priceHighToLow() throws SQLException{
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Listings GROUP BY price ORDER BY DESC" );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Listing> result = new ArrayList<>();
            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");
                String duration = resultSet.getString("duration");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                Listing listing = new Listing(title, description, category, location, price, duration, date, id);
                result.add(listing);
            }
            return result;
        }
    }

    @Override
    public void update(Listing listing) throws SQLException {

    }

    @Override
    public void delete(Listing listing) throws SQLException {

    }
}
