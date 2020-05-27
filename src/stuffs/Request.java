package stuffs;

import java.io.Serializable;

/**
 * A class containing specific information about requests
 * @author Group 2
 */
public class Request implements Serializable
{
  private int id;
  private int itemId;
  private int rentedToId;
  private int rentedFromId;

  public Request(int id, int itemId, int rentedToId,
      int rentedFromId)
  {
    this.id = id;
    this.itemId = itemId;
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