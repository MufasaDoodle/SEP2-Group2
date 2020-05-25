package client.model;

import shared.util.Subject;
import stuffs.Account;
import stuffs.FeedbackToItem;
import stuffs.Listing;

public interface MasterModelInterface extends Subject
{
  Account getAccountById(int id);
  Listing getListingByID(int id);
  int getCurrentItemID();
  void setCurrentItemID(int itemID);
  void setCurrentAccountID(String email);
  int getCurrentAccountID();
  String getCurrentAccountName();
  boolean accountCheck();
  FeedbackToItem getFeedbackById(int id);
  void setFeedbackId(int feedbackId);
  int getFeedbackId();
  int getCurrentChatterID();
  void setCurrentChatterID(int currentChatterID);
  void saveChatterName();
  int getViewingAccountID();
  void setViewingAccountID(int viewingAccountID);
}
