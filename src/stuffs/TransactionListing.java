package stuffs;

import java.io.Serializable;

public class TransactionListing implements Serializable
{
  private String title;
  private String category;
  private double price;
  private String dateFrom;
  private String duration;
  private String accountName;
  private String rentedTo;
  private int rentedFromId;
  private int rentedToId;
  private String status;

  public TransactionListing(String title, String category, double price,
      String dateFrom, String duration, String accountName, String status)
  {
    this.title = title;
    this.category = category;
    this.price = price;
    this.dateFrom = dateFrom;
    this.duration = duration;
    this.accountName = accountName;
    this.status = status;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getTitle()
  {
    return title;
  }

  public String getCategory()
  {
    return category;
  }

  public double getPrice()
  {
    return price;
  }

  public String getDateFrom()
  {
    return dateFrom;
  }

  public String getDuration()
  {
    return duration;
  }

  public String getAccountName()
  {
    return accountName;
  }

  public String getRentedTo()
  {
    return rentedTo;
  }

  public int getRentedFromId()
  {
    return rentedFromId;
  }

  public int getRentedToId()
  {
    return rentedToId;
  }
}
