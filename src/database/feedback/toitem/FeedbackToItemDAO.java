package database.feedback.toitem;

import stuffs.Account;

import stuffs.FeedbackToItem;

import java.sql.SQLException;
import java.util.List;

public interface FeedbackToItemDAO
{
  FeedbackToItem createFeedback(int starRating, int itemId) throws SQLException;
  FeedbackToItem createFeedback(int starRating, String writtenFeedback, int itemId) throws SQLException;
  FeedbackToItem createFeedback(String writtenFeedback, int itemId) throws SQLException;

  /*List<FeedbackToItem> readByLowToHigh() throws SQLException;*/


  void update(FeedbackToItem feedbackToItem)  throws SQLException;
  void delete(FeedbackToItem feedbackToItem)  throws SQLException;
}
