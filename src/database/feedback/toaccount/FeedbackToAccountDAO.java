package database.feedback.toaccount;

import stuffs.FeedbackToAccount;


import java.sql.SQLException;

public interface FeedbackToAccountDAO
{
  FeedbackToAccount createFeedback(int starRating, int itemId) throws SQLException;
  FeedbackToAccount createFeedback(int starRating, String writtenFeedback, int itemId) throws SQLException;
  FeedbackToAccount createFeedback(String writtenFeedback, int itemId) throws SQLException;

  /*List<FeedbackToItem> readByLowToHigh() throws SQLException;*/


  void update(FeedbackToAccount feedbackToItem)  throws SQLException;
  void delete(FeedbackToAccount feedbackToItem)  throws SQLException;
}
