package database.feedback.toitem;

import stuffs.Account;

import stuffs.FeedbackToItem;

import java.sql.SQLException;
import java.util.List;

public interface FeedbackToItemDAO
{
  FeedbackToItem createFeedback(String starRating, String writtenFeedback, int itemId, int accountId, String accountName) throws SQLException;
  List<FeedbackToItem> getFeedback(int itemId) throws SQLException;
  String getAvgStarRating(int itemId) throws SQLException;

  /*List<FeedbackToItem> readByLowToHigh() throws SQLException;*/


  void update(FeedbackToItem feedbackToItem)  throws SQLException;
  void delete(FeedbackToItem feedbackToItem)  throws SQLException;
}
