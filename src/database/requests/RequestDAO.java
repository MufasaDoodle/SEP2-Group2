package database.requests;

import shared.transferobjects.Request;
import shared.transferobjects.RequestListing;

import java.sql.SQLException;
import java.util.List;

public interface RequestDAO
{
  Request create(int itemId, int requestFrom, int requestTo)
      throws SQLException;
  void delete(int id) throws SQLException;
  void deleteDecline(int itemId, int requestFromId) throws SQLException;
  void deleteByAccount(int id) throws SQLException;

  List<RequestListing> getRequestByAccountId(int requestTo) throws SQLException;
  Request getRequest(int itemId, int requestFrom) throws SQLException;
}