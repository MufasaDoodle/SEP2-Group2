package client.model;

import stuffs.FeedbackToItem;

import java.util.List;

public class FeedbackModelManager implements FeedbackModel
{
  private DataModel dataModel;
  private MasterModelInterface master;

  public FeedbackModelManager(DataModel dataModel, MasterModelInterface master)
  {
    this.dataModel = dataModel;
    this.master = master;
  }

  @Override public boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName)
  {
    System.out.println("Feedback was created");
    return dataModel.getClient().createFeedbackItems(itemId, starRating, feedback, accountId, accountName);
  }

  @Override public List<FeedbackToItem> getFeedbackItems(int itemId)
  {
    System.out.println("List of items feedback retrieved");
    return dataModel.getClient().getFeedbackItems(itemId);
  }

  @Override public String getAvgStarRating(int itemId)
  {
    System.out.println("Average star retrieved");
    return dataModel.getClient().getAvgStarRating(itemId);
  }

  @Override public List<Integer> getRentedTo(int itemId)
  {
    return dataModel.getClient().getRentedTo(itemId);
  }

  @Override public void deleteItemFeedback(int id)
  {
    System.out.println("Item feedback deleted");
    dataModel.getClient().deleteItemFeedback(id);
  }

  @Override public void deleteFeedbackByItemId(int id)
  {
    System.out.println("Feedback deleted");
    dataModel.getClient().deleteFeedbackByItemId(id);
  }
}
