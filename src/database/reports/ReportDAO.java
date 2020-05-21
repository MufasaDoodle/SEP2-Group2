package database.reports;

import stuffs.Report;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReportDAO
{
  Report create(int reportFrom, int reportedItemId,
      int reportedAccountId, int reportedItemFeedbackId, Date date) throws
      SQLException;
  List<Report> getAll() throws SQLException;
  void delete(int id) throws SQLException;
  void deleteByAccount(int id) throws SQLException;
  void deleteByItem(int id) throws SQLException;
  void deleteByItemFeedback(int id) throws SQLException;

  Report getByItemId(int id) throws SQLException;
  Report getByFeedbackId(int id) throws SQLException;
  Report getByAccountId(int id) throws SQLException;

}
