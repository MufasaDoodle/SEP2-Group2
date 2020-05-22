package database.feedback.toitem;

import stuffs.FeedbackToItem;

import java.sql.SQLException;
import java.util.List;

public interface FeedbackToItemDAO
{
  FeedbackToItem createFeedback(String starRating, String writtenFeedback,
      int itemId, int accountId, String accountName) throws SQLException;
  List<FeedbackToItem> getFeedback(int itemId) throws SQLException;
  String getAvgStarRating(int itemId) throws SQLException;
  FeedbackToItem getFeedbackById(int id) throws SQLException;

  /*List<FeedbackToItem> readByLowToHigh() throws SQLException;*/

  void update(FeedbackToItem feedbackToItem) throws SQLException;
  void delete(int id) throws SQLException;
  void deleteByItemId(int id) throws SQLException;
}