package shared.transferobjects;

import java.awt.*;
import java.io.Serializable;

public class FeedbackToAccount implements Serializable
{
  private int startRating;
  private String writtenFeedback;
  private int id;
  private int accountId;

  public FeedbackToAccount(int id, int startRating, int accountId){
    this.startRating = startRating;
    this.accountId = accountId;
    this.id = id;
  }

  public FeedbackToAccount(int id,String writtenFeedback, int accountId){
    this.writtenFeedback = writtenFeedback;
    this.accountId = accountId;
    this.id = id;
  }

  public FeedbackToAccount(int id, int startRating, String writtenFeedback,  int accountId){
    this.startRating = startRating;
    this.writtenFeedback = writtenFeedback;
    this.accountId = accountId;
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public int getAccountId()
  {
    return accountId;
  }

  public int getStartRating()
  {
    return startRating;
  }

  public String getWrittenFeedback()
  {
    return writtenFeedback;
  }

}