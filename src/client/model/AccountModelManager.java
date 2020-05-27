package client.model;

import stuffs.Account;
import stuffs.Listing;

import java.io.IOException;
import java.util.List;

/**
 * A class that handles account details and sends them to the view model
 * @author Group 2
 */
public class AccountModelManager implements AccountModel
{
  private DataModel dataModel;
  private MasterModelInterface master;

  public AccountModelManager(DataModel dataModel, MasterModelInterface master)
  {
    this.dataModel = dataModel;
    this.master = master;
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    System.out.println("Account created!");
    return dataModel.getClient().createAccount(name, email, password1, address, phoneNumber);
  }

  @Override public String getAllAccounts()

  {
    return null;
  }

  @Override public boolean checkLogIn(String email, String password)
  {
    return dataModel.getClient().checkLogIn(email, password);
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio)
  {
    System.out.println("Account created! (but not really)");
    return dataModel.getClient().createAccount(name, email, password1, address, phoneNumber, bio);
  }

  @Override public void setCurrentAccountName(String email)
  {
    dataModel.setCurrentAccountName(dataModel.getClient().getAccountName(email));
  }

  @Override public List<Listing> getListingsByAccount(int accountId)
  {
    System.out.println("Owners's listings have been retrieved");
    return dataModel.getClient().getListingsByAccount(accountId);
  }

  @Override public boolean updateAccount(String email, String pass, String address, String number, String bio)
  {
    Account currentAccount = dataModel.getClient().getAccountById(dataModel.getCurrentAccountID());
    Account updatedAccount = new Account(currentAccount.getId(), currentAccount.getName(), email, pass, address, number, bio);
    if (dataModel.getClient().updateAccount(updatedAccount))
    {
      System.out.println("Account updated");
      return true;
    }
    return false;
  }

  @Override public boolean isEmailTaken(String email)
  {
    return dataModel.getClient().isEmailTaken(email);
  }
}
