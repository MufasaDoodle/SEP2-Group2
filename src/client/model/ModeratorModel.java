package client.model;

import stuffs.Account;
import stuffs.Report;

import java.util.List;

public interface ModeratorModel
{
  Account getModeratedAccount();
  void setModeratedAccount(int accountId);
  void setModeratorOpen(boolean whereFrom);
  boolean getModeratorOpen();
  void createReport(int reportFrom, int reportedItemId, int reportedAccountId, int reportedItemFeedbackId, String date);
  List<Report> getAllReports();
  void deleteReport(int id);
  void deleteAccount(int id);
  void deleteMessageByAccount(int id);
  Report getReportByItemId(int id);
  Report getReportByFeedbackId(int id);
  Report getReportByAccountId(int id);
  void deleteReportByAccount(int id);
  void deleteReportByItem(int id);
  void deleteReportByItemFeedback(int id);
}
