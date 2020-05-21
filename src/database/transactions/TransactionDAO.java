package database.transactions;

import stuffs.Transaction;
import stuffs.TransactionListing;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface TransactionDAO
{
  Transaction create(int itemId,Date date, int rentedToId, int rentedFromId)
      throws SQLException;
  Transaction getTransactionByItemId(int itemId) throws SQLException;
  List<Integer> getRentedToId(int itemId) throws SQLException;
  void delete(int id) throws SQLException;
  void deleteByAccount(int id) throws SQLException;
  void deleteByItem(int id) throws SQLException;

  List<TransactionListing> getTransactionByRentedTo(int rentedTo) throws SQLException;
  List<TransactionListing> getTransactionByRentedFrom(int rentedFrom) throws SQLException;


}
