package database.creatingListings;

import stuffs.Listing;

import java.rmi.RemoteException;
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
    if (instance == null)
    {
      instance = new ListingDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5433/projectsep2", "group2",
            "password");
  }

  @Override public Listing create(String title, String description,
      String category, String location, double price, String duration,
      Date date, int accountId, String promoted) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".listings(title, description, category, location, price, duration, date, accountId,rented,promoted) VALUES(?, ?, ?, ?, ?, ?, ?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, title);
      statement.setString(2, description);
      statement.setString(3, category);
      statement.setString(4, location);
      statement.setDouble(5, price);
      statement.setString(6, duration);
      statement.setDate(7, date);
      statement.setInt(8, accountId);
      statement.setString(9,"Available");
      statement.setString(10,promoted);
      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("Listing created in database");
        return new Listing(title, description, category, location, price,
            duration, date.toString(), keys.getInt(8), accountId,"Available",promoted);
      }
      else
        throw new SQLException("No keys generated");
    }
  }

  /*@Override public Listing createRentedListing(String title, String description,
      String category, String location, double price, String duration,
      Date date, int accountId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".listings(title, description, category, location, price, duration, date, accountId) VALUES(?, ?, ?, ?, ?, ?, ?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, title);
      statement.setString(2, description);
      statement.setString(3, category);
      statement.setString(4, location);
      statement.setDouble(5, price);
      statement.setString(6, duration);
      statement.setDate(7, date);
      statement.setInt(8, accountId);
      statement.setString(9,"Rented");
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("Listing created in database");
        return new Listing(title, description, category, location, price,
            duration, date.toString(), keys.getInt(8), accountId,"Rented");
      }
      else
        throw new SQLException("No keys generated");
    }
  }*/

  /*@Override public Listing createWithoutDescription(String title,
      String category, String location, double price, String duration,
      Date date, int accountId,String promoted) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".Listings(title, category, location, price, duration, date,rented) VALUES(?, ?, ?, ?, ?, ?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, title);
      statement.setString(2, category);
      statement.setString(3, location);
      statement.setDouble(4, price);
      statement.setString(5, duration);
      statement.setDate(6, date);
      statement.setString(7,"Available");
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
        return new Listing(title, category, location, price, duration,
            date.toString(), keys.getInt(1), accountId,"Available");
      else
        throw new SQLException("No keys generated");
    }
  }*/

  @Override public Listing readById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        String title = resultSet.getString("title");
        String category = resultSet.getString("category");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        return new Listing(title, description, category, location, price,
            duration, date, id, accountId,rented,promoted);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<Listing> readByAccountId(int accountId)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE accountId = ? AND id>1");
      statement.setInt(1, accountId);
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
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> readByTitle(String title) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND id>1");
      statement.setString(1, "%" + title + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String category = resultSet.getString("category");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, category, location,
            price, duration, date, id, accountId,rented,promoted);;
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitle(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND id>1 AND rented = 'Available'");
      statement.setString(1, "%" + title + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String category = resultSet.getString("category");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, category, location,
            price, duration, date, id, accountId,rented,promoted);;
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> readByCategory(String category)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND id>1");
      statement.setString(1, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldCategory = resultSet.getString("category");
        String title = resultSet.getString("title");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, oldCategory, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableCategory(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND id>1 AND rented = 'Available'");
      statement.setString(1, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldCategory = resultSet.getString("category");
        String title = resultSet.getString("title");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, oldCategory, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> readByLocation(String location)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? AND id>1");
      statement.setString(1, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldLocation = resultSet.getString("location");
        String category = resultSet.getString("category");
        String title = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, oldLocation,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableLocation(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? AND id>1 AND rented = 'Available'");
      statement.setString(1, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldLocation = resultSet.getString("location");
        String category = resultSet.getString("category");
        String title = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, oldLocation,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleCategoryLocation(String title,
      String category, String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? AND id>1");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      statement.setString(3, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleCategory(String title, String category)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND id>1");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            location, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleLocation(String title, String location)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? AND id>1");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldLocation = resultSet.getString("location");
        String category = resultSet.getString("category");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, category,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> categoryLocation(String category,
      String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? AND id>1");
      statement.setString(1, "%" + category + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        String title = resultSet.getString("title");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleCategoryLocation(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available' ");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      statement.setString(3, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleCategory(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND id>1 AND rented = 'Available'");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            location, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleLocation(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available'");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldLocation = resultSet.getString("location");
        String category = resultSet.getString("category");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, category,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableCategoryLocation(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available'");
      statement.setString(1, "%" + category + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        String title = resultSet.getString("title");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> priceLowToHigh() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE id>1 ORDER BY price ASC");
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
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> priceHighToLow() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE id>1 ORDER BY price DESC");
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
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availablePriceHighToLow() throws SQLException{
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE id>1 AND rented = 'Available' ORDER BY price DESC");
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
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availablePriceLowToHigh() throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE id>1 AND rented = 'Available' ORDER BY price ASC");
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
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titlePriceLowToHigh(String title)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titlePriceHighToLow(String title)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitlePriceLowToHigh(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND id>1 AND rented = 'Available' ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitlePriceHighToLow(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND id>1 AND rented = 'Available' ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> categoryPriceLowToHigh(String category)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> categoryPriceHighToLow(String category)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableCategoryPriceLowToHigh(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND rented = 'Available' AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableCategoryPriceHighToLow(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND rented = 'Available' AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> locationPriceLowToHigh(String location)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> locationPriceHighToLow(String location)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableLocationPriceLowToHigh(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? AND rented = 'Available' AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableLocationPriceHighToLow(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? AND rented = 'Available' AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleCategoryLocationPriceLowToHigh(
      String title, String category, String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      statement.setString(3, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleCategoryLocationPriceHighToLow(
      String title, String category, String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      statement.setString(3, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleCategoryLocationPriceLowToHigh(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available' ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      statement.setString(3, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleCategoryLocationPriceHighToLow(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available' ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      statement.setString(3, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleCategoryPriceLowToHigh(String title,
      String category) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleCategoryPriceHighToLow(String title,
      String category) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleCategoryPriceLowToHigh(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND id>1 AND rented = 'Available' ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleCategoryPriceHighToLow(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND id>1 AND rented = 'Available' ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + category + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleLocationPriceLowToHigh(String title,
      String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleLocationPriceHighToLow(String title,
      String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleLocationPriceLowToHigh(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available' ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableTitleLocationPriceHighToLow(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available' ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> categoryLocationPriceLowToHigh(String category,
      String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? AND id>1 ORDER BY price ASC");
      statement.setString(1, "%" + category + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> categoryLocationPriceHighToLow(String category,
      String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? AND id>1 ORDER BY price DESC");
      statement.setString(1, "%" + category + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }
  @Override
  public List<Listing> availableCategoryLocationPriceLowToHigh(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available' ORDER BY price ASC");
      statement.setString(1, "%" + category + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> availableCategoryLocationPriceHighToLow(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? AND id>1 AND rented = 'Available' ORDER BY price DESC");
      statement.setString(1, "%" + category + "%");
      statement.setString(2, "%" + location + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String oldTitle = resultSet.getString("title");
        String oldCategory = resultSet.getString("category");
        String oldLocation = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String description = resultSet.getString("description");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(oldTitle, description, oldCategory,
            oldLocation, price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> isAvailable() throws SQLException{
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings where rented = 'Available' AND id>1");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String category = resultSet.getString("category");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> getAll() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM \"SEP2\".Listings where id > 1 order by promoted DESC");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String category = resultSet.getString("category");
        String location = resultSet.getString("location");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String date = resultSet.getString("date");
        int id = resultSet.getInt("id");
        int accountId = resultSet.getInt("accountId");
        String rented = resultSet.getString("rented");
        String promoted = resultSet.getString("promoted");
        Listing listing = new Listing(title, description, category, location,
            price, duration, date, id, accountId,rented,promoted);
        result.add(listing);
      }
      return result;
    }
  }



  @Override public void update(Listing listing) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE \"SEP2\".Listings SET title = ?, description = ?, category = ?, location = ?, price = ?, duration = ?, date = ?, avgstarrating = ?, accountid = ?, rented = ?, promoted = ? WHERE id=?");
      statement.setString(1, listing.getTitle());
      statement.setString(2, listing.getDescription());
      statement.setString(3, listing.getCategory());
      statement.setString(4, listing.getLocation());
      statement.setDouble(5, listing.getPrice());
      statement.setString(6, listing.getDuration());
      statement.setDate(7, Date.valueOf(listing.getDate()));
      statement.setDouble(8, listing.getAvgStarRating());
      statement.setInt(9, listing.getAccountId());
      statement.setString(10, listing.getRented());
      statement.setString(11,listing.getPromoted());
      statement.setInt(12, listing.getId());
      statement.executeUpdate();
    }
  }
  @Override public void delete(int itemId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".Listings WHERE id = ?");
      statement.setInt(1, itemId);
      statement.executeUpdate();
    }
  }

  @Override public void deleteByAccount(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".Listings WHERE accountid = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }
}