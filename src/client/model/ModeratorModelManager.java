package client.model;

import stuffs.Account;
import stuffs.Report;

import java.util.List;

public class ModeratorModelManager implements ModeratorModel
{
  private DataModel dataModel;
  private MasterModelInterface masterModel;

  public ModeratorModelManager(DataModel dataModel, MasterModelInterface masterModel)
  {
    this.dataModel = dataModel;
    this.masterModel = masterModel;
  }

  @Override public Account getModeratedAccount()
  {
    return dataModel.getModeratedAccount();
  }

  @Override public void setModeratedAccount(int accountId)
  {
    dataModel.setModeratedAccount(masterModel.getAccountById(accountId));
  }

  @Override public void setModeratorOpen(boolean whereFrom)
  {
    dataModel.setFromModeratorOpen(whereFrom);
  }

  @Override public boolean getModeratorOpen()
  {
    return dataModel.isFromModeratorOpen();
  }

  @Override public void createReport(int reportFrom, int reportedItemId, int reportedAccountId, int reportedItemFeedbackId, String date)
  {
    dataModel.getClient().createReport(reportFrom, reportedItemId, reportedAccountId, reportedItemFeedbackId, date);
  }

  @Override public List<Report> getAllReports()
  {
    System.out.println("All reports retrieved");
    return dataModel.getClient().getAllReports();
  }

  @Override public void deleteReport(int id)
  {
    System.out.println("Report deleted");
    dataModel.getClient().deleteReport(id);
  }

  @Override public void deleteAccount(int id)
  {
    System.out.println("Account deleted");
    dataModel.getClient().deleteAccount(id);
  }

  @Override public void deleteMessageByAccount(int id)
  {
    dataModel.getClient().deleteMessageByAccount(id);
  }

  @Override public Report getReportByItemId(int id)
  {
    return dataModel.getClient().getReportByItemId(id);
  }

  @Override public Report getReportByFeedbackId(int id)
  {
    return dataModel.getClient().getReportByFeedbackId(id);
  }

  @Override public Report getReportByAccountId(int id)
  {
    return dataModel.getClient().getReportByAccountId(id);
  }

  @Override public void deleteReportByAccount(int id)
  {
    System.out.println("Report deleted");
    dataModel.getClient().deleteReportByAccount(id);
  }

  @Override public void deleteReportByItem(int id)
  {
    System.out.println("Report deleted");
    dataModel.getClient().deleteReportByItem(id);
  }

  @Override public void deleteReportByItemFeedback(int id)
  {
    System.out.println("Report deleted");
    dataModel.getClient().deleteReportByItemFeedback(id);
  }
}
