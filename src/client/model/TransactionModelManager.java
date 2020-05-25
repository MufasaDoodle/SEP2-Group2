package client.model;

import stuffs.Request;
import stuffs.RequestListing;
import stuffs.Transaction;
import stuffs.TransactionListing;

import java.util.List;

public class TransactionModelManager implements TransactionModel
{
  private DataModel dataModel;
  private MasterModelInterface masterModel;

  public TransactionModelManager(DataModel dataModel, MasterModelInterface masterModel)
  {
    this.dataModel = dataModel;
    this.masterModel = masterModel;
  }

  @Override public void createRequest(int itemId, int requestFrom, int requestTo)
  {
    System.out.println("Request sent");
    dataModel.getClient().createRequest(itemId, requestFrom, requestTo);
  }

  @Override public void deleteRequest(int id)
  {
    System.out.println("Request deleted");
    dataModel.getClient().deleteRequest(id);
  }

  @Override public void deleteDecline(int itemId, int requestFromId)
  {
    System.out.println("Request deleted");
    dataModel.getClient().deleteDecline(itemId, requestFromId);
  }

  @Override public List<RequestListing> getRequestByAccountId(int requestTo)
  {
    return dataModel.getClient().getRequestByAccountId(requestTo);
  }

  @Override public Request getRequest(int itemId, int requestFrom)
  {
    return dataModel.getClient().getRequest(itemId, requestFrom);
  }

  @Override public void createTransaction(int itemId, String date, int rentedToId, int rentedFromId)
  {
    System.out.println("Transaction created");
    dataModel.getClient().createTransaction(itemId, date, rentedToId, rentedFromId);
  }

  @Override public Transaction getTransactionByItemId(int itemId)
  {
    return dataModel.getClient().getTransactionByItemId(itemId);
  }

  @Override public List<TransactionListing> getTransactionByRentedTo(int rentedTo)
  {
    return dataModel.getClient().getTransactionByRentedTo(rentedTo);
  }

  @Override public List<TransactionListing> getTransactionByRentedFrom(int rentedFrom)
  {
    return dataModel.getClient().getTransactionByRentedFrom(rentedFrom);
  }

  @Override public void deleteTransaction(int id)
  {
    System.out.println("Transaction deleted");
    dataModel.getClient().deleteTransaction(id);
  }

  @Override public void deleteTransactionByAccount(int id)
  {
    System.out.println("Transaction deleted");
    dataModel.getClient().deleteTransactionByAccount(id);
  }

  @Override public void deleteTransactionByItem(int id)
  {
    System.out.println("Transaction deleted");
    dataModel.getClient().deleteTransactionByItem(id);
  }

  @Override public void deleteRequestByAccount(int id)
  {
    System.out.println("Request deleted");
    dataModel.getClient().deleteRequestByAccount(id);
  }
}
