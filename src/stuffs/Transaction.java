package stuffs;

import java.io.Serializable;

/**
 * A class containing specific information about transactions
 * @author Group 2
 */
public class Transaction implements Serializable
{
  private int id;
  private int itemId;
  private String date;
  private int rentedToId;
  private int rentedFromId;

  public Transaction(int id, int itemId, String date, int rentedToId,
      int rentedFromId)
  {
    this.id = id;
    this.itemId = itemId;
    this.date = date;
    this.rentedToId = rentedToId;
    this.rentedFromId = rentedFromId;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getItemId()
  {
    return itemId;
  }

  public void setItemId(int itemId)
  {
    this.itemId = itemId;
  }

  public String getDate()
  {
    return date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public int getRentedToId()
  {
    return rentedToId;
  }

  public void setRentedToId(int rentedToId)
  {
    this.rentedToId = rentedToId;
  }

  public int getRentedFromId()
  {
    return rentedFromId;
  }

  public void setRentedFromId(int rentedFromId)
  {
    this.rentedFromId = rentedFromId;
  }
}