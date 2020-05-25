package client.view.accountView;

import client.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import shared.util.EmailCheck;
import stuffs.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AccountViewModel
{
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private AccountModel accountModel;
  private ChatModel chatModel;
  private ModeratorModel moderatorModel;
  private TransactionModel transactionModel;
  private StringProperty name, address, phone, bio, emailEdit, addressEdit, numberEdit, bioEdit, pass1, pass2;
  private ObservableList<Listing> listings;

  private ObservableList<RequestListing> requests;
  private ObservableList<TransactionListing> transactions;
  private boolean isDeclined;

  public AccountViewModel(MasterModelInterface masterModel, ListingsModel listingsModel, AccountModel accountModel, ChatModel chatModel, ModeratorModel moderatorModel, TransactionModel transactionModel)
  {
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.accountModel = accountModel;
    this.chatModel = chatModel;
    this.moderatorModel = moderatorModel;
    this.transactionModel = transactionModel;
    name = new SimpleStringProperty();
    address = new SimpleStringProperty();
    phone = new SimpleStringProperty();
    bio = new SimpleStringProperty();
    emailEdit = new SimpleStringProperty();
    addressEdit = new SimpleStringProperty();
    numberEdit = new SimpleStringProperty();
    bioEdit = new SimpleStringProperty();
    pass1 = new SimpleStringProperty();
    pass2 = new SimpleStringProperty();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public StringProperty addressProperty()
  {
    return address;
  }

  public StringProperty phoneProperty()
  {
    return phone;
  }

  public StringProperty bioProperty()
  {
    return bio;
  }

  public StringProperty emailEditProperty()
  {
    return emailEdit;
  }

  public StringProperty addressEditProperty()
  {
    return addressEdit;
  }

  public StringProperty numberEditProperty()
  {
    return numberEdit;
  }

  public StringProperty bioEditProperty()
  {
    return bioEdit;
  }

  public StringProperty pass1Property()
  {
    return pass1;
  }

  public StringProperty pass2Property()
  {
    return pass2;
  }

  public boolean getIsDeclined()
  {
    return isDeclined;
  }

  public void setIsDeclined(boolean declined)
  {
    isDeclined = declined;
  }

  public void setOwner()
  {
    Account tempCheck = masterModel.getAccountById(masterModel.getCurrentAccountID());
    if (tempCheck == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Your account is banned");
      alert.showAndWait();
    }
    else if (listingsModel.getFromListingViewOpen())
    {
      Account temp = masterModel.getAccountById(masterModel.getCurrentAccountID());
      name.set(temp.getName());
      address.set(temp.getAddress());
      phone.set(temp.getPhoneNumber());
      bio.set(temp.getBio());
    }
    else if (moderatorModel.getModeratorOpen())
    {
      Account temp = moderatorModel.getModeratedAccount();
      name.set(temp.getName());
      address.set(temp.getAddress());
      phone.set(temp.getPhoneNumber());
      bio.set(temp.getBio());
    }
    else if (!listingsModel.getFromListingViewOpen())
    {
      Listing listing = masterModel.getListingByID(masterModel.getCurrentItemID());
      if (listing == null)
      {
        masterModel.setCurrentChatterID(1);
      }
      else
      {
        int itemId = masterModel.getCurrentItemID();
        int accountId = masterModel.getListingByID(itemId).getAccountId();
        Account temp = masterModel.getAccountById(accountId);

        name.set(temp.getName());
        address.set(temp.getAddress());
        phone.set(temp.getPhoneNumber());
        bio.set(temp.getBio());
      }
    }
    else if (!listingsModel.getFromListingViewOpen())
    {
      int itemId = masterModel.getCurrentItemID();
      if (masterModel.getListingByID(itemId) == null)
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Item was not chosen!");
        alert.showAndWait();
      }
      else
      {
        int accountId = masterModel.getListingByID(itemId).getAccountId();
        Account temp = masterModel.getAccountById(accountId);
        name.set(temp.getName());
        address.set(temp.getAddress());
        phone.set(temp.getPhoneNumber());
        bio.set(temp.getBio());
      }
    }
    //Need an avg rate for account
    //avgRate.set(String.valueOf(temp.));
  }

  ObservableList<Listing> getListings()
  {
    return listings;
  }

  public void listOfOwnerListings()
  {
    if (listingsModel.getFromListingViewOpen())
    {
      List<Listing> list = accountModel.getListingsByAccount(masterModel.getCurrentAccountID());
      listings = FXCollections.observableArrayList(list);
    }
    else if (moderatorModel.getModeratorOpen())
    {
      List<Listing> list = accountModel.getListingsByAccount(moderatorModel.getModeratedAccount().getId());
      listings = FXCollections.observableArrayList(list);
    }
    else if (!listingsModel.getFromListingViewOpen())
    {
      Listing temp = masterModel.getListingByID(masterModel.getCurrentItemID());
      if (masterModel.getListingByID(masterModel.getCurrentItemID()) == null)
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Cannot contact no one!");
        alert.showAndWait();
      }
      else
      {
        List<Listing> list = accountModel.getListingsByAccount(masterModel.getAccountById(temp.getAccountId()).getId());
        listings = FXCollections.observableArrayList(list);
      }
    }
    else if (!listingsModel.getFromListingViewOpen())
    {

      if (masterModel.getListingByID(masterModel.getCurrentItemID()) != null)
      {
        Listing temp = masterModel.getListingByID(masterModel.getCurrentItemID());
        List<Listing> list = accountModel.getListingsByAccount(masterModel.getAccountById(temp.getAccountId()).getId());
        listings = FXCollections.observableArrayList(list);
      }
    }
  }

  public void listOfOwnerRentals()
  {
    List<TransactionListing> rentedTo = transactionModel.getTransactionByRentedTo(masterModel.getCurrentAccountID());
    List<TransactionListing> rentedFrom = transactionModel.getTransactionByRentedFrom(masterModel.getCurrentAccountID());

    List<TransactionListing> result = new ArrayList<>();

    result.addAll(rentedTo);
    result.addAll(rentedFrom);
    transactions = FXCollections.observableArrayList(result);
  }

  ObservableList<TransactionListing> getTransactions()
  {
    return transactions;
  }

  public void listOfOwnerRequests()
  {
    List<RequestListing> requestList = transactionModel.getRequestByAccountId(masterModel.getCurrentAccountID());
    requests = FXCollections.observableArrayList(requestList);
  }

  ObservableList<RequestListing> getRequests()
  {
    return requests;
  }

  public void setItem(int itemID)
  {
    masterModel.setCurrentItemID(itemID);
  }

  public void updateEditFields()
  {
    Account account = masterModel.getAccountById(masterModel.getCurrentAccountID());
    emailEdit.set(account.getEmail());
    addressEdit.set(account.getAddress());
    numberEdit.set(account.getPhoneNumber());
    bioEdit.set(account.getBio());
    pass1.set(account.getPassword());
    pass2.set(account.getPassword());
  }

  public void updateAccountInfo(String email, String pass1, String pass2, String address, String number, String bio)
  {
    if (!email.equals("") && !pass1.equals("") && !pass2.equals("") && !address.equals("") && !number.equals("") && !bio.equals(""))
    {
      if (EmailCheck.isValid(email))
      {
        if (pass1.equals(pass2))
        {
          if (accountModel.isEmailTaken(email) && !masterModel.getAccountById(masterModel.getCurrentAccountID()).getEmail().equals(email))
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Email is already in use!");
            alert.setContentText("Please choose another!");
            alert.showAndWait();
          }
          else
          {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Account");
            alert.setHeaderText("Do you want to update your account?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
              if (accountModel.updateAccount(email, pass1, address, number, bio))
              {
                Alert conf = new Alert(Alert.AlertType.INFORMATION);
                conf.setTitle("Account Updated");
                conf.setHeaderText("Account successfully updated!");
                conf.showAndWait();
                setOwner();
              }
              else
              {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Account Update");
                warning.setHeaderText("Account could not be updated, try again later");
                warning.showAndWait();
              }
            }
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warning");
          alert.setHeaderText("Passwords do not match!");
          alert.setContentText("Please check your passwords!");
          alert.showAndWait();
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Email is not valid!");
        alert.setContentText("Please check your email again!");
        alert.showAndWait();
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("All * fields must be filled!");
      alert.setContentText("Please fill all * fields!");
      alert.showAndWait();
    }
  }

  public boolean checkOwner(String name)
  {
    if (chatModel.checkOwner())
    {
      return true;
    }
    return false;
    /*
    if (clientModel.getAccountById(clientModel.getCurrentAccountID()).getName().equals(name))
    {
      return true;
    }
    return false;
     */
  }

  public int getCurrentItemId()
  {
    return masterModel.getCurrentItemID();
  }

  public List<Integer> getDeletedItemIds()
  {
    return listingsModel.getDeletedItemIds();
  }

  public int getAccountID()
  {
    return masterModel.getCurrentAccountID();
  }

  public void acceptRent(int itemId, int rentedTo)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    int rentedFrom = masterModel.getCurrentAccountID();
    transactionModel.createTransaction(itemId, dateFormat.format(date), rentedTo, rentedFrom);
    transactionModel.deleteRequest(itemId);
  }

  public void declineRent(int itemId, int rentedTo)
  {
    transactionModel.deleteDecline(itemId, rentedTo);
  }

  public Request getRequest(int itemId, int requestFrom)
  {
    return transactionModel.getRequest(itemId, requestFrom);
  }

  public Listing getListing(int itemId)
  {
    return masterModel.getListingByID(itemId);
  }

  public void updateListing(String title, String description, String category, String location, String duration, double price, String rented, int itemId, int accountId, String promoted)
  {
    listingsModel.updateListingRented(title, description, category, location, price, duration, rented, itemId, accountId, promoted);
  }

  public void reportAccount()
  {
    if (masterModel.getCurrentAccountID() != 1)
    {
      int reportFrom = masterModel.getCurrentAccountID();
      int itemId = masterModel.getCurrentItemID();
      if (masterModel.getListingByID(itemId) == null)
      {
        int accountId = masterModel.getCurrentAccountID();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        moderatorModel.createReport(reportFrom, 0, accountId, 0, dateFormat.format(date));
      }
      else
      {
        int accountId = masterModel.getListingByID(itemId).getAccountId();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        moderatorModel.createReport(reportFrom, 0, accountId, 0, dateFormat.format(date));
      }
    }
  }

  public boolean itemCheck()
  {
    return masterModel.getListingByID(masterModel.getCurrentItemID()) != null;
  }

  public boolean getReportByAccountId()
  {
    int itemId = masterModel.getCurrentItemID();
    if (masterModel.getListingByID(itemId) != null)
    {
      int accountId = masterModel.getListingByID(itemId).getAccountId();
      return moderatorModel.getReportByAccountId(accountId) == null;
    }
    else
    {
      int accountId = masterModel.getCurrentAccountID();
      return moderatorModel.getReportByAccountId(accountId) == null;
    }

  }

  public boolean accountCheck()
  {
    return masterModel.accountCheck();
  }

}