package stuffs;

import java.io.Serializable;

public class Report implements Serializable
{
  private int id;
  private int reportFrom;
  private int reportedItemId;
  private int reportedAccountId;
  private int reportedItemFeedbackId;
  private String date;

  public Report(int id, int reportFrom, int reportedItemId,
      int reportedAccountId, int reportedItemFeedbackId,
      String date)
  {
    this.id = id;
    this.reportFrom = reportFrom;
    this.reportedItemId = reportedItemId;
    this.reportedAccountId = reportedAccountId;
    this.reportedItemFeedbackId = reportedItemFeedbackId;
    this.date = date;
  }



  public int getReportedItemFeedbackId()
  {
    return reportedItemFeedbackId;
  }

  public int getId()
  {
    return id;
  }

  public int getReportFrom()
  {
    return reportFrom;
  }

  public int getReportedItemId()
  {
    return reportedItemId;
  }

  public int getReportedAccountId()
  {
    return reportedAccountId;
  }

  public String getDate()
  {
    return date;
  }
}