package shared.transferobjects;

import java.io.Serializable;

/**
 * A class containing specific details about feedback for listings
 * @author Group 2
 */
public class FeedbackToItem implements Serializable
{
  private String startRating;
  private String writtenFeedback;
  private int id;
  private int itemId;
  private int accountId;
  private String accountName;

  public FeedbackToItem(int id, String startRating, String writtenFeedback,  int itemId, int accountId, String accountName){
    this.startRating = startRating;
    this.writtenFeedback = writtenFeedback;
    this.itemId = itemId;
    this.id = id;
    this.accountId=accountId;
    this.accountName=accountName;
  }

  public int getId()
  {
    return id;
  }

  public int getItemId()
  {
    return itemId;
  }

  public String getStartRating()
  {
    return startRating;
  }

  public String getWrittenFeedback()
  {
    return writtenFeedback;
  }
  public int getAccountId()
  {
    return accountId;
  }
  public String getAccountName()
  {return accountName;}

}