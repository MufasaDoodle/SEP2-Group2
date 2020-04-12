package stuffs;

public class FeedbackToItem
{
  private int startRating;
  private String writtenFeedback;
  private int id;
  private int itemId;

  public FeedbackToItem(int id, int startRating, int itemId){
    this.startRating = startRating;
    this.itemId = itemId;
    this.id = id;
  }

  public FeedbackToItem(int id,String writtenFeedback, int itemId){
    this.writtenFeedback = writtenFeedback;
    this.itemId = itemId;
    this.id = id;
  }

  public FeedbackToItem(int id, int startRating, String writtenFeedback,  int itemId){
    this.startRating = startRating;
    this.writtenFeedback = writtenFeedback;
    this.itemId = itemId;
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public int getItemId()
  {
    return itemId;
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
