package shared.transferobjects;

import java.io.Serializable;

/**
 * A class containing specific information about requested listings
 * @author Group 2
 */
public class RequestListing implements Serializable
{
  private String title;
  private String category;
  private double price;
  private String duration;
  private String requestFrom;
  private int itemId;
  private int requestFromId;

  public RequestListing(String title, String category, double price,
      String duration, String requestFrom, int itemId,
      int requestFromId)
  {
    this.title = title;
    this.category = category;
    this.price = price;
    this.duration = duration;
    this.requestFrom = requestFrom;
    this.itemId = itemId;
    this.requestFromId = requestFromId;
  }

  public int getItemId()
  {
    return itemId;
  }

  public int getRequestFromId()
  {
    return requestFromId;
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

  public String getDuration()
  {
    return duration;
  }

  public String getRequestFrom()
  {
    return requestFrom;
  }
}