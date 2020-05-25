package client.model;

import stuffs.FeedbackToItem;

import java.util.List;

public interface FeedbackModel
{
  boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName);
  List<FeedbackToItem> getFeedbackItems(int itemId);
  String getAvgStarRating(int itemId);
  List<Integer> getRentedTo(int itemId);
  void deleteItemFeedback(int id);
  void deleteFeedbackByItemId(int id);
}
