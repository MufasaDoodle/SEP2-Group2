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
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projectsep2", "group2", "password");
  }

  @Override public Listing create(String title, String description, String category, String location, double price, String duration, Date date) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".listings(title, description, category, location, price, duration, date) VALUES(?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, title);
      statement.setString(2, description);
      statement.setString(3, category);
      statement.setString(4, location);
      statement.setDouble(5, price);
      statement.setString(6, duration);
      statement.setDate(7, date);
      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("Listing created in database");
        return new Listing(title, description, category, location, price, duration, date.toString(), keys.getInt(8));
      }
      else
        throw new SQLException("No keys generated");
    }
  }

  @Override public Listing createWithoutDescription(String title, String category, String location, double price, String duration, Date date) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".Listings(title, category, location, price, duration, date) VALUES(?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, title);
      statement.setString(3, category);
      statement.setString(4, location);
      statement.setDouble(5, price);
      statement.setString(6, duration);
      statement.setDate(7, date);
      statement.executeUpdate();

      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
        return new Listing(title, category, location, price, duration, date.toString(), keys.getInt(1));
      else
        throw new SQLException("No keys generated");
    }
  }

  @Override public Listing readById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE id = ?");
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
        return new Listing(title, description, category, location, price, duration, date, id);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<Listing> readByTitle(String title) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ?");
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
        Listing listing = new Listing(oldTitle, description, category, location, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> readByCategory(String category) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ?");
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
        Listing listing = new Listing(title, description, oldCategory, location, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> readByLocation(String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE location LIKE ?");
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
        Listing listing = new Listing(title, description, category, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleCategoryLocation(String title, String category, String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ?");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleCategory(String title, String category) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ?");
      statement.setString(1, "%" + title + "%");
      statement.setString(1, "%" + category + "%");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, location, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> titleLocation(String title, String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ?");
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
        Listing listing = new Listing(oldTitle, description, category, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> categoryLocation(String category, String location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ?");
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
        Listing listing = new Listing(title, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override public List<Listing> starRatingLowToHigh() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings ORDER BY avgstarrating ASC");
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

  @Override public List<Listing> starRatingHighToLow() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings ORDER BY avgstarrating DESC");
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

  @Override public List<Listing> priceLowToHigh() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings ORDER BY price ASC");
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

  @Override public List<Listing> priceHighToLow() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings ORDER BY price DESC");
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
  public List<Listing> newToOld() throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings GROUP BY date ORDER BY DESC");
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
  public List<Listing> oldToNew() throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings ORDER BY date ASC");
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
  public List<Listing> titleNewOld(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? ORDER BY date DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleOldNew(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? ORDER BY date ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleRatingLowToHigh(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? ORDER BY avgstarrating ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleRatingHighToLow(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? ORDER BY avgstarrating DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titlePriceLowToHigh(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? ORDER BY price ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titlePriceHighToLow(String title) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? ORDER BY price DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryNewOld(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? ORDER BY date DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryOldNew(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? ORDER BY date ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryRatingLowToHigh(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? ORDER BY avgstarrating ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryRatingHighToLow(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? ORDER BY avgstarrating DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryPriceLowToHigh(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? ORDER BY price ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryPriceHighToLow(String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? ORDER BY price DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> locationNewOld(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? ORDER BY date DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> locationOldNew(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERElocation LIKE ? ORDER BY date ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> locationRatingLowToHigh(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? ORDER BY avgstarrating ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> locationRatingHighToLow(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? ORDER BY avgstarrating DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> locationPriceLowToHigh(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? ORDER BY price ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> locationPriceHighToLow(String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE location LIKE ? ORDER BY price DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryLocationNewOld(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? ORDER BY date DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryLocationOldNew(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? ORDER BY date ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryLocationRatingLowToHigh(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? ORDER BY avgstarrating ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryLocationRatingHighToLow(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? ORDER BY avgstarrating DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryLocationPriceLowToHigh(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? ORDER BY price ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryLocationPriceHighToLow(String title, String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? AND location LIKE ? ORDER BY price DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryNewOld(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? ORDER BY date DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryOldNew(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? ORDER BY date ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryRatingLowToHigh(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? ORDER BY avgstarrating ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryRatingHighToLow(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? ORDER BY avgstarrating DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryPriceLowToHigh(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? ORDER BY price ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleCategoryPriceHighToLow(String title, String category) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND category LIKE ? ORDER BY price DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleLocationNewOld(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? ORDER BY date DESC");
      statement.setString(1, "%" + title + "%");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleLocationOldNew(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? ORDER BY date ASC");
      statement.setString(1, "%" + title + "%");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleLocationRatingLowToHigh(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? ORDER BY avgstarrating ASC");
      statement.setString(1, "%" + title + "%");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleLocationRatingHighToLow(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? ORDER BY avgstarrating DESC");
      statement.setString(1, "%" + title + "%");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleLocationPriceLowToHigh(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? ORDER BY price ASC");
      statement.setString(1, "%" + title + "%");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> titleLocationPriceHighToLow(String title, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE title LIKE ? AND location LIKE ? ORDER BY price DESC");
      statement.setString(1, "%" + title + "%");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryLocationNewOld(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? ORDER BY date DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryLocationOldNew(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? ORDER BY date ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryLocationRatingLowToHigh(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? ORDER BY avgstarrating AVG");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryLocationRatingHighToLow(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? ORDER BY avgstarrating DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryLocationPriceLowToHigh(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? ORDER BY price ASC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> categoryLocationPriceHighToLow(String category, String location) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings WHERE category LIKE ? AND location LIKE ? ORDER BY price DESC");
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
        Listing listing = new Listing(oldTitle, description, oldCategory, oldLocation, price, duration, date, id);
        result.add(listing);
      }
      return result;
    }
  }

  @Override
  public List<Listing> getAll() throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Listings");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Listing> result = new ArrayList<>();
      while (resultSet.next()) {
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

  @Override public void update(Listing listing) throws SQLException
  {

  }

  @Override public void delete(Listing listing) throws SQLException
  {

  }
}
